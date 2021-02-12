package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;

@SuppressWarnings("deprecation")
public class HeldItemListener implements Listener {
	
	@EventHandler
	public void onPlayerHeldItem(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(item == null || item.getType() == Material.AIR) return;
		
		ItemNBT nbt = new ItemNBT(item);
		NBTTagCompound tag = nbt.getTag();
		
		if(tag.hasKey("weapon")) {
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			NBTTagList indetifications = tag.getList("indetifications", 10);
			for(int i = 0; i < indetifications.size(); i++) {
				NBTTagCompound list = indetifications.get(i);
				String name = list.getString("a");
				int value = list.getInt("b");
				
				switch(name) {
					case "health-regen":
						rp.temp_Health_Regen += value;
						break;
					case "mana-regen":
						rp.temp_Mana_Regen += value;
						break;
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if(e.getItemDrop().getItemStack().getType() == Material.ARROW) {
			e.getItemDrop().remove();
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItem().getItemStack();
		if(item == null || item.getType() == Material.AIR) return;
		
		if(item.getType() == Material.ARROW) {
			e.setCancelled(true);
			e.getItem().remove();
			return;
		}
		
		NBTTagCompound tag = new ItemNBT(item).getTag();
		
		if(tag.hasKey("weapon")) {
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			p.getInventory().addItem(RPGItem.updateInfoItem(rp, item, tag));
			e.setCancelled(true);
			e.getItem().remove();
		}
	}
	
}
