package ru.ItzDarth.DarthRPG.spells.spells.archer;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class ArrowStormSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-arrowstorm-name";
	}

	@Override
	public int getCost_I() { // 1 уровень
		return 6;
	}

	@Override
	public int getCost_II() { // 16 уровень
		return 6;
	}

	@Override
	public int getCost_III() { // 36 уровень
		return 6;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		new BukkitRunnable() {
			int arrow = 10;
			public void run() {
				Arrow ar = rp.PLAYER.launchProjectile(Arrow.class);
				ar.setCritical(true);
				ar.setMetadata("arrowStorm", new FixedMetadataValue(DarthRPG.INSTANCE, 1));
				loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1, 1);
				
				if(arrow == 0) {
					this.cancel();
					return;
				}
				
				arrow--;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 3L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		new BukkitRunnable() {
			int arrow = 20;
			public void run() {
				Arrow ar = rp.PLAYER.launchProjectile(Arrow.class);
				ar.setCritical(true);
				ar.setMetadata("arrowStorm", new FixedMetadataValue(DarthRPG.INSTANCE, 2));
				loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1, 1);
				
				if(arrow == 0) {
					this.cancel();
					return;
				}
				
				arrow--;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 3L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		new BukkitRunnable() {
			int arrow = 35;
			public void run() {
				Arrow ar = rp.PLAYER.launchProjectile(Arrow.class);
				ar.setCritical(true);
				ar.setMetadata("arrowStorm", new FixedMetadataValue(DarthRPG.INSTANCE, 3));
				loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1, 1);
				
				if(arrow == 0) {
					this.cancel();
					return;
				}
				
				arrow--;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 3L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}
	
}