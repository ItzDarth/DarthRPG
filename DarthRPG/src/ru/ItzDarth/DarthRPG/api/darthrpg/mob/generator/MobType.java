package ru.ItzDarth.DarthRPG.api.darthrpg.mob.generator;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

import com.google.common.collect.Lists;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.utils.Utils;

public enum MobType {
	
	// При сопротивлении урон делиться на 2
	// При уязвимости урон умножаеться на 2
	ZOMBIE(EntityType.ZOMBIE, "Zombie", Sound.ENTITY_ZOMBIE_HURT, Sound.ENTITY_ZOMBIE_DEATH, 20, Lists.newArrayList(), Lists.newArrayList());
	
	public EntityType TYPE;
	public String NAME;
	public int HP;
	public Sound SOUND_HURT;
	public Sound SOUND_DEATH;
	public List<DamageType> RESISTENCE;
	public List<DamageType> WEAKNES;
	
	MobType(EntityType type, String name, Sound sound_h, Sound sound_d, int hp, List<DamageType> defense, List<DamageType> weaknes) {
		this.TYPE = type;
		this.NAME = name;
		this.HP = hp;
		this.SOUND_HURT = sound_h;
		this.SOUND_DEATH = sound_d;
		
		RESISTENCE = defense;
		
		WEAKNES = weaknes;
	}
	
	public void spawnMob(Location loc, int level, int defense) {
		Entity entity = loc.getWorld().spawnEntity(loc, TYPE);
		entity.setMetadata("enemy", new FixedMetadataValue(DarthRPG.INSTANCE, "a"));
		entity.setMetadata("level", new FixedMetadataValue(DarthRPG.INSTANCE, level));
		entity.setMetadata("defense", new FixedMetadataValue(DarthRPG.INSTANCE, defense));
		entity.setMetadata("hp", new FixedMetadataValue(DarthRPG.INSTANCE, HP*level));
		entity.setMetadata("tag", new FixedMetadataValue(DarthRPG.INSTANCE, this));
		
		entity.setMetadata("resistence", new FixedMetadataValue(DarthRPG.INSTANCE, RESISTENCE));
		entity.setMetadata("weaknes", new FixedMetadataValue(DarthRPG.INSTANCE, WEAKNES));
		
		entity.setMetadata("sounds", new FixedMetadataValue(DarthRPG.INSTANCE, Lists.newArrayList(SOUND_HURT, SOUND_DEATH)));
		
		entity.setCustomName("§7[Lvl "+level+"] §c"+NAME+" §4[§c"+Utils.commas(HP)+"§4]");
		entity.setCustomNameVisible(true);
	}
	
	public void updateTag(Entity entity, int level, int hp) {
		entity.setCustomName("§7[Lvl "+level+"] §c"+NAME+" §4[§c"+(hp < 0 ? 0 : Utils.commas(hp))+"§4]");
	}
	
}

