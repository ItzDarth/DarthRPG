package ru.ItzDarth.DarthRPG.spells.spells.warrior;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class UppercutSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-uppercut-name";
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
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = tag.getInt("physical")*3;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		int THUNDER = tag.getInt("thunder") > 0 ? (int) (tag.getInt("thunder") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 8) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0, -0.5), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc.clone().add(0.3, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc.clone().add(0, 0, -0.3), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, 0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0.5, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, 0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0.5, -0.5), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, -0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, -0.5, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, -0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, -0.5, -0.5), 25, 0, 0, 0, 0.0D);
			 	
		 		loc.getWorld().playSound(loc, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							Vector unitVector = new Vector(entity.getLocation().getDirection().getX(), entity.getLocation().getDirection().getY()+3, entity.getLocation().getDirection().getZ());
							unitVector = unitVector.normalize();             
							entity.setVelocity(unitVector);
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.EARTH, EARTH},
		        				   {DamageType.THUNDER, THUNDER},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1.9F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = tag.getInt("physical")*3;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		int THUNDER = tag.getInt("thunder") > 0 ? (int) (tag.getInt("thunder") * (20.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 8) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0, -0.5), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc.clone().add(0.3, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc.clone().add(0, 0, -0.3), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, 0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0.5, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, 0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0.5, -0.5), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, -0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, -0.5, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, -0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, -0.5, -0.5), 25, 0, 0, 0, 0.0D);
			 	
		 		loc.getWorld().playSound(loc, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							Vector unitVector = new Vector(entity.getLocation().getDirection().getX(), entity.getLocation().getDirection().getY()+3, entity.getLocation().getDirection().getZ());
							unitVector = unitVector.normalize();             
							entity.setVelocity(unitVector);
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.EARTH, EARTH},
		        				   {DamageType.THUNDER, THUNDER},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1.9F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = tag.getInt("physical")*3;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		int THUNDER = tag.getInt("thunder") > 0 ? (int) (tag.getInt("thunder") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 8) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0, -0.5), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc.clone().add(0.3, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc.clone().add(0, 0, -0.3), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, 0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0.5, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, 0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, 0.5, -0.5), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0.5, -0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, -0.5, 0.5), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(-0.5, -0.5, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.DRIP_LAVA, loc.clone().add(0, -0.5, -0.5), 25, 0, 0, 0, 0.0D);
			 	
		 		loc.getWorld().playSound(loc, Sound.BLOCK_LAVA_EXTINGUISH, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							Vector unitVector = new Vector(entity.getLocation().getDirection().getX(), entity.getLocation().getDirection().getY()+3, entity.getLocation().getDirection().getZ());
							unitVector = unitVector.normalize();             
							entity.setVelocity(unitVector);
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.EARTH, EARTH},
		        				   {DamageType.THUNDER, THUNDER},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1.9F);
	}
	
}
