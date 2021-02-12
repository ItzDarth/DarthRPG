package ru.ItzDarth.DarthRPG.spells.spells.warrior;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class ChargeSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-charge-name";
	}

	@Override
	public int getCost_I() { // 1 уровень
		return 4;
	}

	@Override
	public int getCost_II() { // 16 уровень
		return 4;
	}

	@Override
	public int getCost_III() { // 36 уровень
		return 4;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		int PHYSICAL = tag.getInt("physical")*2;
		
		createHelix(rp.PLAYER);
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL}
    			   });
    		}
		});
		
		Vector v = loc.getDirection().multiply(1.5);
		v.setY(v.getY()+0.4);
		
		rp.PLAYER.setVelocity(v);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		int PHYSICAL = tag.getInt("physical")*2;
		int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (40.0F / 100.0F)) : (int) (tag.getInt("physical") * (25.0F / 100.0F));
		
		createHelix(rp.PLAYER);
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL},
    				   {DamageType.FIRE, FIRE},
    			   });
    		}
		});
		
		Vector v = loc.getDirection().multiply(1.5);
		v.setY(v.getY()+0.4);
		
		rp.PLAYER.setVelocity(v);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		int PHYSICAL = tag.getInt("physical")*2;
		int FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (60.0F / 100.0F)) : (int) (tag.getInt("physical") * (35.0F / 100.0F));
		
		createHelix(rp.PLAYER);
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL},
    				   {DamageType.FIRE, FIRE},
    			   });
    		}
		});
		
		Vector v = loc.getDirection().multiply(1.5);
		v.setY(v.getY()+0.4);
		
		rp.PLAYER.setVelocity(v);
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}
	
	private static double phi = Math.PI/8;
	
	private void createHelix(Player p) {
		double x;
		double y;
		double z;
		Location loc = p.getLocation();
		for(double t = 0; t<= 2*Math.PI; t += Math.PI/16) {
			for(double i = 0; i <=1; i += 1) {
				x = 0.3 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
				y = 0.5 * t;
				z = 0.3 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
				loc.add(x,y,z);
				DarthParticle.spawnParticle(loc.getWorld(), ParticleType.FLAME, loc, 5, 0, 0, 0, 0.0D);
				loc.subtract(x,y,z);
			}
		}
	}
	
}
