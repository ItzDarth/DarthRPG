package ru.ItzDarth.DarthRPG.api.darthrpg.mob;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.event.KillMobEvent;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.generator.MobType;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.hologram.Hologram;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class DamageAPI {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void damage(Player p, Entity entity, Object[][] damagers) {
		int defense = entity.getMetadata("defense").get(0).asInt();
		int hp = entity.getMetadata("hp").get(0).asInt();
		
		List<Sound> sounds = (List<Sound>) entity.getMetadata("sounds").get(0).value();
		
		if(hp <= 0) {
			if(entity.isInvulnerable()) {
				entity.remove();
				return;
			}
			entity.playEffect(EntityEffect.DEATH);
			Bukkit.getPluginManager().callEvent(new KillMobEvent(p, entity));
			entity.getWorld().playSound(entity.getLocation(), sounds.get(1), 1, 1);
			return;
		}
		
		int lastHP = 0;
		
		List<DamageType> resistence = (List<DamageType>) entity.getMetadata("resistence").get(0).value();
		List<DamageType> weaknes = (List<DamageType>) entity.getMetadata("weaknes").get(0).value();
		
		StringBuilder builder = new StringBuilder();
		
		for(Object[] damageInfo : damagers) {
			DamageType type = (DamageType) damageInfo[0];
			int damage = (int) damageInfo[1];
			
			if(damage <= 0) continue;
			
			damage = resistence.contains(type) ? damage / 2 : (weaknes.contains(type) ? damage * 2 : damage);
			
			builder.append(type.STARTWITHDAMAGE.replace("{x}", Utils.commas(damage)));
			
			if(type == DamageType.PHYSICAL) {
				lastHP = defense > 0 ? hp + (defense / 2) - damage : hp-damage;
			} else {
				lastHP = defense > 0 ? lastHP + (defense / 2) - damage : lastHP-damage;
			}
		}
		
		Location holoLoc = entity.getLocation().clone().add(0, 0.3, 0);
		
		Hologram holo = new Hologram(new String[] { builder.toString() }, holoLoc);
		holo.showAllPlayerAtTemp(1);
		
		RPGPlayer rp = RPGApi.getRPGPlayer(p);
		rp.noPvpTime = 5;
		
		if(lastHP <= 0) {
			((MobType) entity.getMetadata("tag").get(0).value()).updateTag(entity, (int) entity.getMetadata("level").get(0).value(), lastHP);
			entity.playEffect(EntityEffect.DEATH);
			entity.getWorld().playSound(entity.getLocation(), sounds.get(1), 1, 1);
			Bukkit.getPluginManager().callEvent(new KillMobEvent(p, entity));
			return;
		}
		
		entity.setMetadata("hp", new FixedMetadataValue(DarthRPG.INSTANCE, lastHP));
		((MobType) entity.getMetadata("tag").get(0).value()).updateTag(entity, (int) entity.getMetadata("level").get(0).value(), lastHP);
		entity.playEffect(EntityEffect.HURT);
		entity.getWorld().playSound(entity.getLocation(), sounds.get(0), 1, 1);
	}
	
}
