package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.BuildItem;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.build.BuildAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.Rank;

public class InteractListener implements Listener {
	
	@EventHandler
	public void onItemInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		RPGPlayer rp = RPGApi.getRPGPlayer(p);
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(item == null || item.getType() == Material.AIR) return;
			Block block = e.getClickedBlock();
			
			if(rp.RANK == Rank.ADMIN || rp.RANK == Rank.BUILDER) {
				switch(item.getType()) {
					case COAL:
					case STICK:
					case PUMPKIN:
					case IRON_ORE:
					case CAULDRON: {
						for(BuildItem bitem : BuildAPI.ITEMS.values()) {
							if(bitem.check(p, item, block)) {
								break;
							}
						}
						break;
					}
					default:
						break;
					}
			}
		}
	}
	
	@EventHandler
	public void onEntityInteract(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof ArmorStand && e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			ItemStack item = p.getInventory().getItemInMainHand();
			
			if(rp.RANK == Rank.ADMIN || rp.RANK == Rank.BUILDER) {
				ArmorStand en = (ArmorStand) e.getEntity();
				switch(item.getType()) {
					case BLAZE_ROD: {
								for(ArmorStand stand : BuildAPI.STANDS) {
									if(stand.getLocation().getX() == en.getLocation().getX() && stand.getLocation().getZ() == en.getLocation().getZ()) {
										float yaw = en.getLocation().getYaw();
										if(yaw == 0) {
											Location g = stand.getLocation();
											g.setYaw(45);
											stand.teleport(g);
										}
										else if(yaw == 45) {
											Location g = stand.getLocation();
											g.setYaw(90);
											stand.teleport(g);
										}
										else if(yaw == 90) {
											Location g = stand.getLocation();
											g.setYaw(135);
											stand.teleport(g);
										}
										else if(yaw == 135) {
											Location g = stand.getLocation();
											g.setYaw(180);
											stand.teleport(g);
										}
										else if(yaw == 180) {
											Location g = stand.getLocation();
											g.setYaw(-135);
											stand.teleport(g);
										}
										else if(yaw == -135) {
											Location g = stand.getLocation();
											g.setYaw(-90);
											stand.teleport(g);
										}
										else if(yaw == -90) {
											Location g = stand.getLocation();
											g.setYaw(-45);
											stand.teleport(g);
										}
										else if(yaw == -45) {
											Location g = stand.getLocation();
											g.setYaw(0);
											stand.teleport(g);
										}
										BuildAPI.saveConfig();
										break;
									}
						}
						break;
					}
					case BLAZE_POWDER: {
								for(ArmorStand stand : BuildAPI.STANDS) {
									if(stand.getLocation().getX() == en.getLocation().getX() && stand.getLocation().getZ() == en.getLocation().getZ()) {
										en.remove();
										BuildAPI.saveConfig();
										break;
									}
						}
						break;
					}
					default:
						break;
					}
			}
		}
	}
	
	@EventHandler
	public void onArmorStandInteract(PlayerArmorStandManipulateEvent e) {
		e.setCancelled(true);
	}
	
}
