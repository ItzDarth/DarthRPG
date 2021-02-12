package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.event.KillMobEvent;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.level.LevelManager;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.hologram.Hologram;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;

public class EntityDeathListener implements Listener {
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			Entity entity = e.getEntity();
			ItemStack item = p.getInventory().getItemInMainHand();
			
			e.setCancelled(true);
			
			if(item == null || item.getType() == Material.AIR) return;
			
			if(p.hasCooldown(item.getType())) return;
			
			NBTTagCompound tag = new ItemNBT(item).getTag();
			
			if(tag.hasKey("weapon")) {
				RPGPlayer rp = RPGApi.getRPGPlayer(p);
				if(rp.CLASS == zClass.MAGE || rp.CLASS == zClass.ARCHER || rp.CLASS == zClass.SHAMAN) return;
				
				zClass Class = zClass.valueOf(tag.getString("class"));
				if(Class == zClass.MAGE || Class == zClass.ARCHER) return;
				int PHYSICAL = tag.getInt("physical");
				int EARTH = tag.getInt("earth");
				int THUNDER = tag.getInt("thunder");
				int WATER = tag.getInt("water");
				int FIRE = tag.getInt("fire");
				int AIR = tag.getInt("air");
				
				p.setCooldown(item.getType(), 15);
				
				if(entity.hasMetadata("enemy")) {
					DamageAPI.damage(p, entity, new Object[][] {
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
		else if(e.getDamager() instanceof Arrow) {
			Arrow damager = (Arrow) e.getDamager();
			Player p = (Player) damager.getShooter();
			Entity entity = e.getEntity();
			
			if(damager.hasMetadata("arrowshield")) return;
			
			ItemStack item = p.getInventory().getItemInMainHand();
			
			boolean hasArrowStorm = damager.hasMetadata("arrowStorm");
			int storm_gradle = 0;
			if(hasArrowStorm) {
				storm_gradle = (int) damager.getMetadata("arrowStorm").get(0).value();
			}
			boolean hasArrowBomb = damager.hasMetadata("arrowBomb");
			int bomb_gradle = 0;
			if(hasArrowBomb) {
				bomb_gradle = (int) damager.getMetadata("arrowBomb").get(0).value();
			}
			
			if(!damager.isDead()) damager.remove();
			e.setCancelled(true);
			
			if(item == null || item.getType() == Material.AIR) return;
			
			NBTTagCompound tag = new ItemNBT(item).getTag();
			
			if(tag.hasKey("weapon")) {
				int PHYSICAL = 0;
				int EARTH = 0;
				int THUNDER = 0;
				int WATER = 0;
				int FIRE = 0;
				int AIR = 0;
				if(hasArrowStorm) {
					PHYSICAL = tag.getInt("physical")/2;
					FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
					if(storm_gradle > 1) {
						THUNDER = tag.getInt("thunder") > 0 ? (int) (tag.getInt("thunder") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
					}
				} else if(hasArrowBomb) {
					PHYSICAL = (int) (tag.getInt("physical")*2.5);
					if(bomb_gradle == 1) {
						FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
						EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
					} else if(bomb_gradle == 2) {
						FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
						EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
					} else if(bomb_gradle == 3) {
						FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
						EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
					}
					DarthParticle.spawnParticle(p.getWorld(), ParticleType.EXPLOSION_LARGE, p.getLocation(), 3);
					for(Entity ert : p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 3, 4, 3)) {
						if(entity == ert) continue;
						if(ert.hasMetadata("enemy")) {
							 DamageAPI.damage(p, ert, new Object[][] {
			    				   {DamageType.PHYSICAL, PHYSICAL},
			    				   {DamageType.FIRE, FIRE},
			    				   {DamageType.EARTH, EARTH},
			    			   });
			    		}
					}
				} else {
					PHYSICAL = tag.getInt("physical");
					EARTH = tag.getInt("earth");
					THUNDER = tag.getInt("thunder");
					WATER = tag.getInt("water");
					FIRE = tag.getInt("fire");
					AIR = tag.getInt("air");
				}
				
				if(entity.hasMetadata("enemy")) {
					DamageAPI.damage(p, entity, new Object[][] {
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
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
	    Arrow arrow = (Arrow) e.getEntity();
	    
	    if(e.getHitBlock() == null || e.getHitBlock().getType() == Material.AIR) return;
	    
	    if(arrow.hasMetadata("arrowshield")) return;
	    
	    boolean hasArrowBomb = arrow.hasMetadata("arrowBomb");
		int bomb_gradle = 0;
		if(!hasArrowBomb) {
			if(!arrow.isDead()) arrow.remove();
			return;
		}
		bomb_gradle = (int) arrow.getMetadata("arrowBomb").get(0).value();
		
		Player p = (Player) arrow.getShooter();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(item == null || item.getType() == Material.AIR) return;
		
		NBTTagCompound tag = new ItemNBT(item).getTag();
		
		if(!arrow.isDead()) arrow.remove();
		
		int PHYSICAL = (int) (tag.getInt("physical")*2.5);
		int FIRE = 0;
		int EARTH = 0;
		if(bomb_gradle == 1) {
			FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
			EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
		} else if(bomb_gradle == 2) {
			FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (15.0F / 100.0F)) : (int) (tag.getInt("physical") * (10.0F / 100.0F));
			EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		} else if(bomb_gradle == 3) {
			FIRE = tag.getInt("fire") > 0 ? (int) (tag.getInt("fire") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
			EARTH = tag.getInt("earth") > 0 ? (int) (tag.getInt("earth") * (25.0F / 100.0F)) : (int) (tag.getInt("physical") * (15.0F / 100.0F));
		}
		DarthParticle.spawnParticle(p.getWorld(), ParticleType.EXPLOSION_LARGE, p.getLocation(), 3);
		for(Entity ert : p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 3, 4, 3)) {
			if(ert.hasMetadata("enemy")) {
				 DamageAPI.damage(p, ert, new Object[][] {
    				   {DamageType.PHYSICAL, PHYSICAL},
    				   {DamageType.FIRE, FIRE},
    				   {DamageType.EARTH, EARTH},
    			   });
    		}
		}
	}
	
	@EventHandler
	public void onProjectileLaunch(EntityShootBowEvent e) {
	    e.setCancelled(true);
	}
	
	@EventHandler
	public void onMobDeath(KillMobEvent e) {
		Entity entity = e.getEntity();
		Player p = e.getPlayer();
		if(entity.hasMetadata("enemy")) {
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			int level = (int) entity.getMetadata("level").get(0).value();
			int d = rp.level-level;
			
			int xp = 0;
			if(d >= 15) xp = 0;
			else xp = Math.abs((d == 0 ? 1 : d)*10);
			
			Location holoLoc = entity.getLocation().clone().add(0, -0.3, 0);
			
			Hologram holo = new Hologram(new String[] {rp.LANGUAGE.getMessage("mob-kill-holo").replace("{x}", xp+""), p.getDisplayName()}, holoLoc);
			holo.showAllPlayerAtTemp(3);
			
			rp.currentXP+=xp;
			if(rp.currentXP >= rp.needXP) {
				rp.currentXP -= rp.needXP;
				rp.levelUp();
				rp.needXP = LevelManager.LEVELS[rp.level][1];
			}
			
			entity.setInvulnerable(true);
			
			new BukkitRunnable() {
				public void run() {
					entity.setInvulnerable(false);
					entity.remove();
				}
			}.runTaskLater(DarthRPG.INSTANCE, 10L);
		}
	}
	
}
