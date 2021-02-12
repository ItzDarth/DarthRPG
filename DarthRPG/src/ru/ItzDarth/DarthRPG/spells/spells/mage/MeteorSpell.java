package ru.ItzDarth.DarthRPG.spells.spells.mage;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class MeteorSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-meteor-name";
	}

	@Override
	public int getCost_I() {
		return 8;
	}

	@Override
	public int getCost_II() {
		return 8;
	}

	@Override
	public int getCost_III() {
		return 8;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) { // 4 сек.
		Location loc = rp.PLAYER.getLocation();
		
		new BukkitRunnable() {
			Location start = loc.clone().add(0, 20, 0);
			int y = start.getBlockY();
			
			int later = 18;
			public void run() {
				for (int i = 0; i < 15; i++) {
				    double angle = 2 * Math.PI * i / 10;
				    Location point = loc.clone().add(4.0D * Math.sin(angle), 0.0D, 4.0D * Math.cos(angle));
				    DarthParticle.spawnParticle(point.getWorld(), ParticleType.FIREWORKS_SPARK, point, 1, 0, 0, 0, 0.0D);
				}
				if(later != 0) {
					later--;
					return;
				}
				y--;
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.LAVA, start.getX(), y, start.getZ(), 27);
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.SMOKE_NORMAL, start.getX(), y, start.getZ(), 20, 0, 0, 0, 0.1F);
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.EXPLOSION_LARGE, start.getX(), y, start.getZ(), 2);
				start.getWorld().playSound(new Location(start.getWorld(), start.getBlockX(), y, start.getZ()), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
				if(y == loc.getBlockY()) {
					DarthParticle.spawnParticle(start.getWorld(), ParticleType.EXPLOSION_HUGE, start.getX(), y, start.getZ(), 2);
					start.getWorld().getNearbyEntities(loc, 4, 4, 4).forEach(entity -> {
						int PHYSICAL = tag.getInt("physical")*3;
						int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (20.0F / 100.0F)) : tag.getInt("physical") / 3;
						int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (20.0F / 100.0F)) : tag.getInt("physical") / 3;
						if(entity.hasMetadata("enemy")) {
							 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.EARTH, EARTH},
		        				   {DamageType.FIRE, FIRE},
		        			   });
		        		}
					});
					this.cancel();
				}
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 3L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 1.9F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) { // 7 блоков
		Location loc = rp.PLAYER.getLocation();
		
		int PHYSICAL = tag.getInt("physical")*3;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (20.0F / 100.0F)) : tag.getInt("physical") / 3;
		int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (20.0F / 100.0F)) : tag.getInt("physical") / 3;
		
		new BukkitRunnable() {
			Location start = loc.clone().add(0, 15, 0);
			int y = start.getBlockY();
			
			int later = 12;
			public void run() {
				for (int i = 0; i < 15; i++) {
				    double angle = 2 * Math.PI * i / 10;
				    Location point = loc.clone().add(4.0D * Math.sin(angle), 0.0D, 4.0D * Math.cos(angle));
				    DarthParticle.spawnParticle(point.getWorld(), ParticleType.FIREWORKS_SPARK, point, 1, 0, 0, 0, 0.0D);
				}
				if(later != 0) {
					later--;
					return;
				}
				y--;
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.LAVA, start.getX(), y, start.getZ(), 27);
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.SMOKE_NORMAL, start.getX(), y, start.getZ(), 20, 0, 0, 0, 0.1F);
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.EXPLOSION_LARGE, start.getX(), y, start.getZ(), 2);
				start.getWorld().playSound(new Location(start.getWorld(), start.getBlockX(), y, start.getZ()), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
				if(y == loc.getBlockY()) {
					DarthParticle.spawnParticle(start.getWorld(), ParticleType.EXPLOSION_HUGE, start.getX(), y, start.getZ(), 2);
					start.getWorld().getNearbyEntities(loc, 4, 4, 4).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.EARTH, EARTH},
		        				   {DamageType.FIRE, FIRE},
		        			   });
		        		}
					});
					this.cancel();
				}
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 3L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 1.9F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) { // 9 блоков
		Location loc = rp.PLAYER.getLocation();
		
		int PHYSICAL = tag.getInt("physical")*3;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (20.0F / 100.0F)) : tag.getInt("physical") / 3;
		int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (20.0F / 100.0F)) : tag.getInt("physical") / 3;
		
		new BukkitRunnable() {
			Location start = loc.clone().add(0, 15, 0);
			int y = start.getBlockY();
			
			int later = 8;
			public void run() {
				for (int i = 0; i < 15; i++) {
				    double angle = 2 * Math.PI * i / 10;
				    Location point = loc.clone().add(4.0D * Math.sin(angle), 0.0D, 4.0D * Math.cos(angle));
				    DarthParticle.spawnParticle(point.getWorld(), ParticleType.FIREWORKS_SPARK, point, 1, 0, 0, 0, 0.0D);
				}
				if(later != 0) {
					later--;
					return;
				}
				y--;
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.LAVA, start.getX(), y, start.getZ(), 27);
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.SMOKE_NORMAL, start.getX(), y, start.getZ(), 20, 0, 0, 0, 0.1F);
				DarthParticle.spawnParticle(start.getWorld(), ParticleType.EXPLOSION_LARGE, start.getX(), y, start.getZ(), 2);
				start.getWorld().playSound(new Location(start.getWorld(), start.getBlockX(), y, start.getZ()), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
				if(y == loc.getBlockY()) {
					DarthParticle.spawnParticle(start.getWorld(), ParticleType.EXPLOSION_HUGE, start.getX(), y, start.getZ(), 2);
					start.getWorld().getNearbyEntities(loc, 4, 4, 4).forEach(entity -> {
						if(entity.hasMetadata("enemy")) {
							 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
		        				   {DamageType.PHYSICAL, PHYSICAL},
		        				   {DamageType.EARTH, EARTH},
		        				   {DamageType.FIRE, FIRE},
		        			   });
		        		}
					});
					this.cancel();
				}
			}
		}.runTaskTimer(DarthRPG.INSTANCE, 0L, 3L);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 1.9F);
	}

}
