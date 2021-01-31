package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import ru.ItzDarth.DarthRPG.api.banmanager.BanManager;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;

public class ResourcePackListener implements Listener {
	
	@EventHandler
	public void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent e) {
		Player p = e.getPlayer();
		Status status = e.getStatus();
		
		if(status == Status.DECLINED) {
			BanManager.kick(p, String.join("\n", RPGApi.getRPGPlayer(p).LANGUAGE.getListMessage("loading-rp-declined")));
		}
		else if(status == Status.FAILED_DOWNLOAD) {
			BanManager.kick(p, String.join("\n", RPGApi.getRPGPlayer(p).LANGUAGE.getListMessage("loading-rp-failed")));
		}
		else if(status == Status.SUCCESSFULLY_LOADED) {
			RPGApi.getRPGPlayer(p).checkIfClass();
		}
	}
	
}
