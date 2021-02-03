package ru.ItzDarth.DarthRPG.spells.attacks;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Sound;
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

public class MageAttack implements Attack {
	
	public zClass getCLASS() {
		return zClass.MAGE;
	}
	
	private Color clr = Color.fromRGB(110, 110, 110);
	
	public void run(RPGPlayer rp, ItemStack item, NBTTagCompound tag) {
		rp.PLAYER.setCooldown(item.getType(), 15);
		
		Location loc = rp.PLAYER.getLocation();
		Vector direction = loc.getDirection().normalize();
		loc.getWorld().playSound(loc, Sound.ENTITY_ENDERPEARL_THROW, 1, 1);
		
        for(double d = 1; d <= 8; d += 0.5) {
           double x = direction.getX() * d;
		   double y = direction.getY() * d + 1.5;
		   double z = direction.getZ() * d;
		   loc.add(x,y,z);
		   
           DarthParticle.spawnParticle(loc.getWorld(), ParticleType.REDSTONE, loc, 5, 0, 0, 0, clr);
           DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CRIT, loc, 1, 0, 0, 0, 0D);
           if(d == 1.0 || d == 2.0 || d == 3.0 || d == 4.0 || d == 5.0 || d == 6.0 || d == 7.0) {
        	   int PHYSICAL = tag.getInt("physical");
			   int EARTH = tag.getInt("earth");
			   int THUNDER = tag.getInt("thunder");
			   int WATER = tag.getInt("water");
			   int FIRE = tag.getInt("fire");
			   int AIR = tag.getInt("air");
        	   loc.getWorld().getNearbyEntities(loc, 1, 1, 1).forEach(entity -> {
        		   if(entity.hasMetadata("enemy")) {
        			   // Дописать!!
        			   DamageAPI.damage(rp.PLAYER, entity, DamageType.PHYSICAL, PHYSICAL);
        			   if(EARTH > 0) DamageAPI.damage(rp.PLAYER, entity, DamageType.EARTH, EARTH);
        			   if(THUNDER > 0) DamageAPI.damage(rp.PLAYER, entity, DamageType.THUNDER, THUNDER);
        			   if(WATER > 0) DamageAPI.damage(rp.PLAYER, entity, DamageType.WATER, WATER);
        			   if(FIRE > 0) DamageAPI.damage(rp.PLAYER, entity, DamageType.FIRE, FIRE);
        			   if(AIR > 0) DamageAPI.damage(rp.PLAYER, entity, DamageType.AIR, AIR);
        		   }
        	   });
           }
           loc.subtract(x,y,z);
        }
	}
	
}
