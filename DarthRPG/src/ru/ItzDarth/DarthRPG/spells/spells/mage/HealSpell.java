package ru.ItzDarth.DarthRPG.spells.spells.mage;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class HealSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-heal-name";
	}

	@Override
	public int getCost_I() { // 1 уровень
		return 8;
	}

	@Override
	public int getCost_II() { // 16 уровень
		return 7;
	}

	@Override
	public int getCost_III() { // 36 уровень
		return 6;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) { // 15%
		int hp = (int) (rp.maxHealth *(15.0F / 100.0F));
		System.out.println(hp);
		int sum = rp.health+hp;
		if(sum > rp.maxHealth) {
			rp.health = rp.maxHealth;
		} else {
			rp.health = sum;
		}
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1.5F);
		createHelix(rp.PLAYER);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) { // 20%
		int hp = (int) (rp.maxHealth *(20.0F / 100.0F));
		int sum = rp.health+hp;
		if(sum > rp.maxHealth) {
			rp.health = rp.maxHealth;
		} else {
			rp.health = sum;
		}
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1.5F);
		createHelix(rp.PLAYER);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) { // 20%
		int hp = (int) (rp.maxHealth *(20.0F / 100.0F));
		int sum = rp.health+hp;
		if(sum > rp.maxHealth) {
			rp.health = rp.maxHealth;
		} else {
			rp.health = sum;
		}
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1.5F);
		createHelix(rp.PLAYER);
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
				DarthParticle.spawnParticle(loc.getWorld(), ParticleType.VILLAGER_HAPPY, loc, 1);
				loc.subtract(x,y,z);
			}
		}
	}

}
