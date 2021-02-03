package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;

@SuppressWarnings("deprecation")
public class HeldItemListener implements Listener {
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItem().getItemStack();
		if(item == null || item.getType() == Material.AIR) return;
		
		NBTTagCompound tag = new ItemNBT(item).getTag();
		
		if(tag.hasKey("weapon")) {
			RPGPlayer rp = RPGApi.getRPGPlayer(p);
			p.getInventory().addItem(RPGItem.updateInfoItem(rp, item, tag));
			e.setCancelled(true);
			e.getItem().remove();
		}
	}
	
}
