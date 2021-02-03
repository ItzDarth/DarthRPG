package ru.ItzDarth.DarthRPG.spells.spells.mage;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class TeleportSpell implements Spell {

	@Override
	public String getKeyName() {
		return "spell-teleport-name";
	}

	@Override
	public int getCost_I() {
		return 4;
	}

	@Override
	public int getCost_II() {
		return 4;
	}

	@Override
	public int getCost_III() {
		return 4;
	}

	@Override
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag) { // 5 блоков
		Location loc = rp.PLAYER.getLocation();
		Vector dir = loc.getDirection();
		dir.normalize();
		dir.multiply(10);
		loc.add(dir);
		if(loc.getBlock().getType() != Material.AIR) {
			for(int y = loc.getBlockY(); y <= 255; y++) {
				Block block = loc.getWorld().getBlockAt(loc.getBlockX(), y, loc.getBlockZ());
				if(block.getType() != Material.AIR) {
					continue;
				} else {
					Location lf = block.getLocation();
					lf.setYaw(loc.getYaw());
					lf.setPitch(loc.getPitch());
					rp.PLAYER.teleport(lf);
					break;
				}
			}
		} else {
			rp.PLAYER.teleport(loc);
		}
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1.5F);
		createHelix(rp.PLAYER);
	}

	@Override
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag) { // 7 блоков
		Location loc = rp.PLAYER.getLocation();
		Vector dir = loc.getDirection();
		dir.normalize();
		dir.multiply(17);
		loc.add(dir);
		if(loc.getBlock().getType() != Material.AIR) {
			for(int y = loc.getBlockY(); y <= 255; y++) {
				Block block = loc.getWorld().getBlockAt(loc.getBlockX(), y, loc.getBlockZ());
				if(block.getType() != Material.AIR) {
					continue;
				} else {
					Location lf = block.getLocation();
					lf.setYaw(loc.getYaw());
					lf.setPitch(loc.getPitch());
					rp.PLAYER.teleport(lf);
					break;
				}
			}
		} else {
			rp.PLAYER.teleport(loc);
		}
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1.5F);
		createHelix(rp.PLAYER);
	}

	@Override
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag) { // 9 блоков
		Location loc = rp.PLAYER.getLocation();
		Vector dir = loc.getDirection();
		dir.normalize();
		dir.multiply(19);
		loc.add(dir);
		if(loc.getBlock().getType() != Material.AIR) {
			for(int y = loc.getBlockY(); y <= 255; y++) {
				Block block = loc.getWorld().getBlockAt(loc.getBlockX(), y, loc.getBlockZ());
				if(block.getType() != Material.AIR) {
					continue;
				} else {
					Location lf = block.getLocation();
					lf.setYaw(loc.getYaw());
					lf.setPitch(loc.getPitch());
					rp.PLAYER.teleport(lf);
					break;
				}
			}
		} else {
			rp.PLAYER.teleport(loc);
		}
		rp.PLAYER.playSound(rp.PLAYER.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1.5F);
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
				DarthParticle.spawnParticle(loc.getWorld(), ParticleType.CRIT_MAGIC, loc, 1);
				loc.subtract(x,y,z);
			}
		}
	}

}
