package ru.ItzDarth.DarthRPG.spells.spells.archer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class ArrowShieldSpell implements Spell {
	
	private static ItemStack shield = new ItemCreator(Material.DIAMOND_HOE).setDurability(2007).setUnbreakable(true).build();
	
	@Override
	public String getKeyName() {
		return "spell-arrowshield-name";
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
		
		add(rp, loc, 1);
		
		new BukkitRunnable() {
			int sec = 300;
			int tick = 0;
			public void run() {
				tick++;
				sec--;
				if(rp == null || rp.PLAYER == null || !(rp.PLAYER.isOnline())) {
					if(rp.archerShield != null || !(rp.archerShield.isDead())) rp.archerShield.remove();
					rp.archerShield = null;
					this.cancel();
					return;
				}
				if(sec == 0) {
					if(rp.archerShield != null || !(rp.archerShield.isDead())) rp.archerShield.remove();
					rp.archerShield = null;
					rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1F);
					this.cancel();
					return;
				}
	            if(rp.archerShield == null || rp.archerShield.isDead()) {
	            	rp.archerShield = null;
	            	this.cancel();
	            	return;
	            }
	            
	            Location loc = getLocationAroundCircle(rp.PLAYER.getLocation(), 1.2, (1.9/20f) * tick);
	            Location loc2 = getLocationAroundCircle2(rp.PLAYER.getLocation(), 1, (1.9/20f) * tick).add(0, 1, 0);
	            DarthParticle.spawnParticle(loc.getWorld(), ParticleType.SPELL_WITCH, loc2, 1, 0, 0, 0, 0.0D);
	            rp.archerShield.teleport(loc);
                sec--;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		add(rp, loc, 2);
		
		new BukkitRunnable() {
			int sec = 300;
			int tick = 0;
			public void run() {
				tick++;
				sec--;
				if(rp == null || rp.PLAYER == null || !(rp.PLAYER.isOnline())) {
					if(rp.archerShield != null || !(rp.archerShield.isDead())) rp.archerShield.remove();
					rp.archerShield = null;
					this.cancel();
					return;
				}
				if(sec == 0) {
					if(rp.archerShield != null || !(rp.archerShield.isDead())) rp.archerShield.remove();
					rp.archerShield = null;
					rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1F);
					this.cancel();
					return;
				}
	            if(rp.archerShield == null || rp.archerShield.isDead()) {
	            	rp.archerShield = null;
	            	this.cancel();
	            	return;
	            }
	            
	            Location loc = getLocationAroundCircle(rp.PLAYER.getLocation(), 1.2, (1.9/20f) * tick);
	            Location loc2 = getLocationAroundCircle2(rp.PLAYER.getLocation(), 1, (1.9/20f) * tick).add(0, 1, 0);
	            DarthParticle.spawnParticle(loc.getWorld(), ParticleType.SPELL_WITCH, loc2, 1, 0, 0, 0, 0.0D);
	            rp.archerShield.teleport(loc);
                sec--;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		add(rp, loc, 3);
		
		new BukkitRunnable() {
			int sec = 300;
			int tick = 0;
			public void run() {
				tick++;
				sec--;
				if(rp == null || rp.PLAYER == null || !(rp.PLAYER.isOnline())) {
					if(rp.archerShield != null || !(rp.archerShield.isDead())) rp.archerShield.remove();
					rp.archerShield = null;
					this.cancel();
					return;
				}
				if(sec == 0) {
					if(rp.archerShield != null || !(rp.archerShield.isDead())) rp.archerShield.remove();
					rp.archerShield = null;
					rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1F);
					this.cancel();
					return;
				}
	            if(rp.archerShield == null || rp.archerShield.isDead()) {
	            	rp.archerShield = null;
	            	this.cancel();
	            	return;
	            }
	            
	            Location loc = getLocationAroundCircle(rp.PLAYER.getLocation(), 1.2, (1.9/20f) * tick);
	            Location loc2 = getLocationAroundCircle2(rp.PLAYER.getLocation(), 1, (1.9/20f) * tick).add(0, 1, 0);
	            DarthParticle.spawnParticle(loc.getWorld(), ParticleType.SPELL_WITCH, loc2, 1, 0, 0, 0, 0.0D);
	            rp.archerShield.teleport(loc);
                sec--;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}
	
	public void add(RPGPlayer rp, Location loc, int gradle) {
		rp.archerShield = loc.getWorld().spawn(loc.clone().add(0, 50, 0), ArmorStand.class);
		rp.archerShield.setMetadata("archershield", new FixedMetadataValue(DarthRPG.INSTANCE, gradle));
		rp.archerShield.setInvulnerable(true);
		rp.archerShield.setGravity(false);
		rp.archerShield.setAI(false);
		rp.archerShield.setArms(true);
		rp.archerShield.getEquipment().setItemInMainHand(shield);
		rp.archerShield.setVisible(false);
	}
	
	public Location getLocationAroundCircle(Location center, double radius, double angleInRadian) {
        double x = center.getX() + radius * Math.cos(angleInRadian);
        double z = center.getZ() + radius * Math.sin(angleInRadian);
        double y = center.getY();

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector());
        loc.setDirection(difference);

        return loc;
    }
	
	public Location getLocationAroundCircle2(Location center, double radius, double angleInRadian) {
        double x = center.getX() + radius * Math.cos(angleInRadian-0.6);
        double z = center.getZ() + radius * Math.sin(angleInRadian-0.6);
        double y = center.getY();

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector());
        loc.setDirection(difference);

        return loc;
    }
	
}