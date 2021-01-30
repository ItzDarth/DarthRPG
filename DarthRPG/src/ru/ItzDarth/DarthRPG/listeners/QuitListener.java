package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;

public class QuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		RPGApi.removeRPGPlayer(p).remove();
	}
	
}
