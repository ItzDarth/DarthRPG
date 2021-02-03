package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;

public class EntityDeathListener implements Listener {
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent e) {
		Entity entity = e.getEntity();
		Player p = ((LivingEntity) entity).getKiller();
		if(entity.hasMetadata("enemy")) {
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			int level = entity.getMetadata("level").get(0).asInt();
			int d = rp.level-level;
			
			int xp = 0;
			if(d >= 15) xp = 0;
			else {
				xp = Math.abs(d*10);
			}
		}
	}
	
}
