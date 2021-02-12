package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.timers.main.MainTimer;

public class PlayerDamageListener implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			e.setCancelled(true);
			
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			
			if(rp.archerShield != null) {
				int i = (int) rp.archerShield.getMetadata("archershield").get(0).value();
				if(i <= 1) {
					rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1F);
					rp.archerShield.remove();
					rp.archerShield = null;
					return;
				}
				rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1F);
				rp.archerShield.setMetadata("archershield", new FixedMetadataValue(DarthRPG.INSTANCE, i-1));
				return;
			}
			rp.health -= e.getDamage();
			p.playEffect(EntityEffect.HURT);
			if(rp.CAST_SEC > 0 || rp.SHOW_SPELL > 0) {
				MainTimer.updateSpellActionBar(rp);
			} else {
				MainTimer.updateDefaultActionBar(rp);
			}
		} else {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			e.setCancelled(true);
		}
	}
	
}
