package ru.ItzDarth.DarthRPG.api.darthrpg.mob;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import net.minecraft.server.v1_12_R1.DamageSource;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;

public class DamageAPI {
	
	public static void damage(Player p, Entity entity, DamageType type, int damage) {
		// Дописать!! Пока что без учета брони и лвл моба,
		// и без учета остальных дамаг по стихиям.
		int defense = entity.getMetadata("defense").get(0).asInt();
		int hp = entity.getMetadata("hp").get(0).asInt();
		int lastHP = 0;
		if(type == DamageType.PHYSICAL) {
			lastHP = defense > 0 ? hp + (defense / 2) - damage : hp-damage;
		}
		if(lastHP <= 0) {
			((LivingEntity) entity).damage(100000, p);
			return;
		}
		entity.setMetadata("hp", new FixedMetadataValue(DarthRPG.INSTANCE, lastHP));
		((CraftEntity) entity).getHandle().damageEntity(DamageSource.GENERIC, 0);
	}
	
}
