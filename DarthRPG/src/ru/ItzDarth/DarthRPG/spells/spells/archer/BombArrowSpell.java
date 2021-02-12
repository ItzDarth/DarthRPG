package ru.ItzDarth.DarthRPG.spells.spells.archer;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.metadata.FixedMetadataValue;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class BombArrowSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-bombarrow-name";
	}

	@Override
	public int getCost_I() { // 1 уровень
		return 8;
	}

	@Override
	public int getCost_II() { // 16 уровень
		return 8;
	}

	@Override
	public int getCost_III() { // 36 уровень
		return 8;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		Arrow ar = rp.PLAYER.launchProjectile(Arrow.class);
		ar.setCritical(true);
		ar.setMetadata("arrowBomb", new FixedMetadataValue(DarthRPG.INSTANCE, 1));
		loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1, 1);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		Arrow ar = rp.PLAYER.launchProjectile(Arrow.class);
		ar.setCritical(true);
		ar.setMetadata("arrowBomb", new FixedMetadataValue(DarthRPG.INSTANCE, 2));
		loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1, 1);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		Arrow ar = rp.PLAYER.launchProjectile(Arrow.class);
		ar.setCritical(true);
		ar.setMetadata("arrowBomb", new FixedMetadataValue(DarthRPG.INSTANCE, 3));
		loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1, 1);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}
	
}