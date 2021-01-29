package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		RPGPlayer rp = new RPGPlayer(p);
		
		p.setMetadata("info", new FixedMetadataValue(DarthRPG.INSTANCE, rp));
	}
	
}
