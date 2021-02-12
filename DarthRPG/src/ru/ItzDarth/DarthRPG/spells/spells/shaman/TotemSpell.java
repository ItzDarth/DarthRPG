package ru.ItzDarth.DarthRPG.spells.spells.shaman;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.metadata.FixedMetadataValue;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.utils.MathL;

public class TotemSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-totem-name";
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

	@SuppressWarnings("deprecation")
	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) {
		Location loc = rp.PLAYER.getLocation().clone().add(0, 2, 0);
		
		FallingBlock block = loc.getWorld().spawnFallingBlock(loc, Material.PURPUR_SLAB, (byte) 1);
		block.setMetadata("totem", new FixedMetadataValue(DarthRPG.INSTANCE, rp.PLAYER));
		block.setDropItem(false);
		block.setVelocity(MathL.calculateVelocity(
				loc.toVector().normalize(), 
				loc.getDirection().normalize().multiply(14), 
				4
				));
		
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.5F);
	}
	
}
