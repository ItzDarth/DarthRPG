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

public class WarScreamScroll implements Spell {

	@Override
	public String getKeyName() {
		return "spell-warscream-name";
	}

	@Override
	public int getCost_I() { // 1 уровень
		return 7;
	}

	@Override
	public int getCost_II() { // 16 уровень
		return 6;
	}

	@Override
	public int getCost_III() { // 36 уровень
		return 5;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = tag.getInt("physical")*2;
		int AIR = tag.getInt("air") > 0 ? (int) (tag.getInt("air") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 13) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d + 1.5;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_LARGE, loc, 1, 0, 0, 0, 0.0D);
			 	
		 		loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.AIR, AIR},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_WITHER_HURT, 1, 1.9F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = tag.getInt("physical")*2;
		int AIR = tag.getInt("air") > 0 ? (int) (tag.getInt("air") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (75.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 15) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d + 1.5;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_LARGE, loc, 1, 0, 0, 0, 0.0D);
			 	
		 		loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.AIR, AIR},
		        				   {DamageType.FIRE, FIRE},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_WITHER_HURT, 1, 1.9F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = tag.getInt("physical")*2;
		int AIR = tag.getInt("air") > 0 ? (int) (tag.getInt("air") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (25.0F / 100.0F));
		int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (75.0F / 100.0F)) : (int) (tag.getInt("physical") * (25.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 18) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d + 1.5;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_LARGE, loc, 1, 0, 0, 0, 0.0D);
			 	
		 		loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.AIR, AIR},
		        				   {DamageType.FIRE, FIRE},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_WITHER_HURT, 1, 1.9F);
	}
	
}
