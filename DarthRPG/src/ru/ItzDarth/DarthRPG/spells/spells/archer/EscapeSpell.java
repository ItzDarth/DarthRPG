package ru.ItzDarth.DarthRPG.spells.spells.archer;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class EscapeSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-escape-name";
	}

	@Override
	public int getCost_I() { // 1 уровень
		return 3;
	}

	@Override
	public int getCost_II() { // 16 уровень
		return 3;
	}

	@Override
	public int getCost_III() { // 36 уровень
		return 3;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		Vector directionVector = loc.getDirection().normalize();
		rp.PLAYER.setVelocity(rp.PLAYER.getVelocity().add(directionVector.multiply(-2)));
		
		rp.PLAYER.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 2));
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		Vector directionVector = loc.getDirection().normalize();
		rp.PLAYER.setVelocity(rp.PLAYER.getVelocity().add(directionVector.multiply(-2.5)));
		
		rp.PLAYER.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 2));
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		
		Vector directionVector = loc.getDirection().normalize();
		rp.PLAYER.setVelocity(rp.PLAYER.getVelocity().add(directionVector.multiply(-3)));
		
		rp.PLAYER.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 2));
		
		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_LARGE, loc, 2);
		int AIR = tag.getInt("air") > 0 ? (int) (tag.getInt("air") * (50.0F / 100.0F)) : (int) (tag.getInt("physical") * (35.0F / 100.0F));
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.AIR, AIR},
    			   });
    		}
		});
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}
	
}