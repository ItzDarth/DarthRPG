package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.DamageAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;
import ru.ItzDarth.DarthRPG.api.particle.DarthParticle;
import ru.ItzDarth.DarthRPG.api.particle.ParticleType;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class ShamanTotemListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockState(EntityChangeBlockEvent e) {
		if(e.getEntity() instanceof FallingBlock) {
	        if(e.getEntity().hasMetadata("totem")) {
	        	e.setCancelled(true);
	        	Player p = (Player) e.getEntity().getMetadata("totem").get(0).value();
	        	if(p == null || !(p.isOnline())) {
	        		return;
	        	}
	        	Location bcenter = Utils.getCenter(e.getBlock().getLocation());
	        	Location center = bcenter.clone().add(0, 1, 0);
	        	
	        	Block block = bcenter.getBlock();
	        	block.setType(Material.PURPUR_SLAB);
	        	block.setData((byte) 1);
	        	
	        	Block barrier = center.getBlock();
	        	barrier.setType(Material.BARRIER);
	        	
	        	DarthParticle.spawnParticle(block.getWorld(), ParticleType.TOTEM, center, 16);
	        	block.getWorld().playSound(block.getLocation(), Sound.ITEM_TOTEM_USE, 1, 0.7F);
	        	
	        	RPGPlayer rp = RPGApi.getRPGPlayer(p);
        		ItemStack item = rp.PLAYER.getInventory().getItemInMainHand();
        		int[] damage = new int[] {3, 3};
        		
        		if(item != null && item.getType() != Material.AIR) {
        			NBTTagCompound tag = new ItemNBT(item).getTag();
        			damage[0] = tag.getInt("physical") > 0 ? (int) (tag.getInt("physical") * (20.0F / 100.0F)) : 3;
        			damage[1] = tag.getInt("air") > 0 ? (int) (tag.getInt("air") * (20.0F / 100.0F)) : damage[0];
        		}
	        	
	        	new BukkitRunnable() {
	        		//  +  =  -
	        		//  *  *  *
	        		//  *  *  *
	        		//  *  *  *
	        		//
	        		//  То есть мы уже рассчитали все дистанции, и просто прибавляем их к нужней локации и храним в массив
	        		Location[] locs = new Location[] {
	        			center.clone().add(0.4, 0.7, 0),
	        			center.clone().add(0, 0.7, 0.4),
	        			center.clone().add(-0.4, 0.7, 0),
	        			center.clone().add(0, 0.7, -0.4),
	        			center.clone().add(0.4, 0.7, 0.4),
	        			center.clone().add(0.4, 0.7, -0.4),
	        			center.clone().add(-0.4, 0.7, 0.4),
	        			center.clone().add(-0.4, 0.7, -0.4),
	        		};
	        		int i = 0;
	        		public void run() {
	        			if(p == null || !(p.isOnline())) {
	        				this.cancel();
	        				return;
	        			}
	        			
	        			if(i % 4 == 0) {
		        			for(Location l : locs) {
		        				DarthParticle.spawnParticle(block.getWorld(), ParticleType.TOTEM, l, 2, 0, 0, 0, 0.0D);
		        			}
	        			}
	        			
	        			if(i % 20 == 0) {
	        				for (int i = 0; i < 15; i++) {
		    				    double angle = 2 * Math.PI * i / 10;
		    				    Location point = center.clone().add(6.0D * Math.sin(angle), 0.0D, 6.0D * Math.cos(angle));
		    				    DarthParticle.spawnParticle(point.getWorld(), ParticleType.VILLAGER_HAPPY, point, 1, 0, 0, 0, 0.0D);
		    				}
	        				for(Entity entity : center.getWorld().getNearbyEntities(center, 8, 8, 8)) {
	        					if(entity.hasMetadata("enemy") && entity.getLocation().distance(center) <= 6) {
	        						 DamageAPI.damage(p, entity, new Object[][] {
	  		        				   	{DamageType.PHYSICAL, damage[0]},
	  		        				   	{DamageType.AIR, damage[1]},
	        						 });
	        					}
	        				}
	        			}
	        			
	        			Location loc = getLocationAroundCircle(center, 6, (2.3/20f) * i);
	        			DarthParticle.spawnParticle(block.getWorld(), ParticleType.TOTEM, loc, 1, 0, 0, 0, 0.0D);
	        			
	        			if(i == 200) {
	        				block.setType(Material.AIR);
	        				barrier.setType(Material.AIR);
	        				this.cancel();
	        				return;
	        			}
	        			i++;
	        		}
	        	}.runTaskTimer(DarthRPG.INSTANCE, 0L, 1L);
	        }
	    }
	}
	
	public Location getLocationAroundCircle(Location center, double radius, double angleInRadian) {
        double x = center.getX() + radius * Math.cos(angleInRadian);
        double z = center.getZ() + radius * Math.sin(angleInRadian);
        double y = center.getY();

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector());
        loc.setDirection(difference);

        return loc;
    }
	
}
