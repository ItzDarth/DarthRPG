package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		RPGApi.addRPGPlayer(p);
	}
	
}
