package ru.ItzDarth.DarthRPG.spells.spells.mage;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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

public class IceSnakeSpell implements Spell {

	private static Color white = Color.WHITE;
	private static Color white_blue = Color.fromRGB(97, 158, 255);
	
	@Override
	public String getKeyName() {
		return "spell-icesnake-name";
	}

	@Override
	public int getCost_I() {
		return 6;
	}

	@Override
	public int getCost_II() {
		return 5;
	}

	@Override
	public int getCost_III() {
		return 4;
	}
	
	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) { // 4 сек.
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 16) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d + 1.5;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	ThreadLocalRandom th = ThreadLocalRandom.current();
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc, 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, 0, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, 0, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc.clone().add(0.3, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc.clone().add(0, 0, -0.3), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, 0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0.5, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, 0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0.5, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, -0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, -0.5, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, -0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, -0.5, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
			 	
		 		loc.getWorld().playSound(loc, Sound.BLOCK_GLASS_BREAK, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							int PHYSICAL = (int) (tag.getInt("physical") * (70.0F / 100.0F));
							int WATER = tag.getInt("water") > 0 ? (int) (tag.getInt("water") * (30.0F / 100.0F)) : (int) (tag.getInt("physical") * (30.0F / 100.0F));
							 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.WATER, WATER},
		        			   });
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 1.9F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) { // 7 блоков
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = (int) (tag.getInt("physical") * (70.0F / 100.0F));
		int WATER = tag.getInt("water") > 0 ? (int) (tag.getInt("water") * (50.0F / 100.0F)) : (int) (tag.getInt("physical") * (50.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 16) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d + 1.5;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	ThreadLocalRandom th = ThreadLocalRandom.current();
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc, 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, 0, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, 0, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc.clone().add(0.3, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc.clone().add(0, 0, -0.3), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, 0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0.5, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, 0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0.5, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, -0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, -0.5, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, -0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, -0.5, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
			 	
		 		loc.getWorld().playSound(loc, Sound.BLOCK_GLASS_BREAK, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.WATER, WATER},
		        			   });
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 1.9F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) { // 9 блоков
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		
		int PHYSICAL = (int) (tag.getInt("physical") * (85.0F / 100.0F));
		int WATER = tag.getInt("water") > 0 ? (int) (tag.getInt("water") * (50.0F / 100.0F)) : (int) (tag.getInt("physical") * (50.0F / 100.0F));
		
		new BukkitRunnable() {
			double d = 1;
			public void run() {
				if(d == 16) {
					this.cancel();
					return;
				}
				double x = direction.getX() * d;
				double y = direction.getY() * d + 1.5;
				double z = direction.getZ() * d;
			 	loc.add(x,y,z);
			 	
			 	ThreadLocalRandom th = ThreadLocalRandom.current();
			 	
			 	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc, 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, 0, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, 0, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc, 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc.clone().add(0.3, 0, 0), 25, 0, 0, 0, 0.0D);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CLOUD, loc.clone().add(0, 0, -0.3), 25, 0, 0, 0, 0.0D);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, 0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0.5, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, 0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, 0.5, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0.5, -0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, -0.5, 0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(-0.5, -0.5, 0), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
		 		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc.clone().add(0, -0.5, -0.5), 25, 0, 0, 0, th.nextBoolean() ? white : white_blue);
			 	
		 		loc.getWorld().playSound(loc, Sound.BLOCK_GLASS_BREAK, 1, 1.9F);
		 		
			 	if(d % 2 == 0) {
					loc.getWorld().getNearbyEntities(loc, 2, 4, 2).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 70, 0));
							DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.WATER, WATER},
		        			});
		        		}
					});
			 	}
			 	
		        loc.subtract(x,y,z);
		        d += 0.5;
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0, 1L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_SNOW_BREAK, 1, 1.9F);
	}

}
