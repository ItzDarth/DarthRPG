package ru.ItzDarth.DarthRPG.spells.spells.warrior;

import org.bukkit.Location;
import org.bukkit.Sound;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class BashSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-bash-name";
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
		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_HUGE, loc, 5);
		
		int PHYSICAL = tag.getInt("physical")*2;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (20.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL},
    				   {DamageType.EARTH, EARTH},
    			   });
    		}
		});
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1.5F);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_HUGE, loc, 5);
		
		int PHYSICAL = tag.getInt("physical")*2;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (40.0F / 100.0F)) : (int) (tag.getInt("physical") * (20.0F / 100.0F));
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL},
    				   {DamageType.EARTH, EARTH},
    			   });
    		}
		});
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1.5F);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation();
		DarthParticle.spawnParticle(loc.getWorld(), ParticleType.EXPLOSION_HUGE, loc, 5);
		
		int PHYSICAL = tag.getInt("physical")*3;
		int EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (50.0F / 100.0F)) : (int) (tag.getInt("physical") * (20.0F / 100.0F));
		
		loc.getWorld().getNearbyEntities(loc, 3, 4, 3).forEach(entity -> {
			if(entity.hasMetadata("enemy")) {
				 DamageAPI.damage(rp.PLAYER, entity, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL},
    				   {DamageType.EARTH, EARTH},
    			   });
    		}
		});
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1.5F);
	}
	
}
