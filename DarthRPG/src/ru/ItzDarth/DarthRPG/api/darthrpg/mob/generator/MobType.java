package ru.ItzDarth.DarthRPG.api.darthrpg.mob.generator;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

import ru.ItzDarth.DarthRPG.DarthRPG;

public enum MobType {
	
	// При сопротивлении урон делиться на 2
	// При уязвимости урон умножаеться на 2
	ZOMBIE(EntityType.ZOMBIE, "Zombie", 20, new Boolean[] {false, false, false, false, false}, new Boolean[] {false, false, false, false, false});
	
	public EntityType TYPE;
	public String NAME;
	public int HP;
	public boolean defenseEARTH,defenseTHUNDER,defenseWATER,defenseFIRE,defenseAIR;
	public boolean weaknesEARTH,weaknesTHUNDER,weaknesWATER,weaknesFIRE,weaknesAIR;
	
	MobType(EntityType type, String name, int hp, Boolean[] defense, Boolean[] weaknes) {
		this.TYPE = type;
		this.NAME = name;
		this.HP = hp;
		
		defenseEARTH = defense[0];
		defenseTHUNDER = defense[1];
		defenseWATER = defense[2];
		defenseFIRE = defense[3];
		defenseAIR = defense[4];
		
		weaknesEARTH = weaknes[0];
		weaknesTHUNDER = weaknes[1];
		weaknesWATER = weaknes[2];
		weaknesFIRE = weaknes[3];
		weaknesAIR = weaknes[4];
	}
	
	public void spawnMob(Location loc, int level, int defense) {
		Entity entity = loc.getWorld().spawnEntity(loc, TYPE);
		entity.setMetadata("enemy", new FixedMetadataValue(DarthRPG.INSTANCE, "a"));
		entity.setMetadata("level", new FixedMetadataValue(DarthRPG.INSTANCE, level));
		entity.setMetadata("defense", new FixedMetadataValue(DarthRPG.INSTANCE, defense));
		entity.setMetadata("hp", new FixedMetadataValue(DarthRPG.INSTANCE, HP*level));
		entity.setCustomName("§7[Lvl "+level+"] §c"+NAME);
		entity.setCustomNameVisible(true);
	}
	
}

