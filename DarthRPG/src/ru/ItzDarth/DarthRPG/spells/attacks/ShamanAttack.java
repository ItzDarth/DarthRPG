package ru.ItzDarth.DarthRPG.spells.attacks;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Attack;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class ShamanAttack implements Attack {
	
	public zClass getCLASS() {
		return zClass.WARRIOR;
	}
	
	public void run(RPGPlayer rp, ItemStack item, NBTTagCompound tag) {
		rp.PLAYER.setCooldown(item.getType(), 15);
		
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		loc.getWorld().playSound(loc, Sound.ENTITY_ENDERPEARL_THROW, 1, 1);
		
		String face = Utils.getCardinalDirection(rp.PLAYER);
		
		int PHYSICAL = tag.getInt("physical");
		int EARTH = tag.getInt("earth");
		int THUNDER = tag.getInt("thunder");
		int WATER = tag.getInt("water");
		int FIRE = tag.getInt("fire");
		int AIR = tag.getInt("air");
		
		// S ES SW N WN NE
		Vector directionTwo = direction.clone().add(new Vector(0, 0, 0.5));
		Vector directionThree = direction.clone().add(new Vector(0, 0, -0.5));
		if(face.equals("S") || face.equals("ES") || face.equals("SW") || face.equals("N") || face.equals("WN") || face.equals("NE")) {
			directionTwo = direction.clone().add(new Vector(0.5, 0, 0));
			directionThree = direction.clone().add(new Vector(-0.5, 0, 0));
		}
		
        for(double d = 1; d <= 8; d += 0.5) {
        	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CRIT_MAGIC, loc.clone().add(directionTwo.getX()*d, directionTwo.getY()*d+1.5, directionTwo.getZ()*d), 5, 0, 0, 0, 0D);
        	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CRIT_MAGIC, loc.clone().add(directionThree.getX()*d, directionThree.getY()*d+1.5, directionThree.getZ()*d), 5, 0, 0, 0, 0D);
        	
        	double x = direction.getX() * d;
        	double y = direction.getY() * d + 1.5;
        	double z = direction.getZ() * d;
        	loc.add(x,y,z);
        	
        	DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CRIT_MAGIC, loc, 5, 0, 0, 0, 0D);
           
        	if(d == 1.0 || d == 2.0 || d == 3.0 || d == 4.0 || d == 5.0) {
        		for(Entity entity : loc.getWorld().getNearbyEntities(loc, 0.49, 1, 0.49)) {
        			if(entity.hasMetadata("enemy")) {
        				DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
        					{DamageType.PHYSICAL, PHYSICAL},
        					{DamageType.EARTH, EARTH},
        					{DamageType.THUNDER, THUNDER},
        					{DamageType.WATER, WATER},
        					{DamageType.FIRE, FIRE},
        					{DamageType.AIR, AIR},
        				});
        			}
        		}
        	}
        	loc.subtract(x,y,z);
        }
	}
	
}
