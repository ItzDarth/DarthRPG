package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import ru.ItzDarth.DarthRPG.api.inventory.ClickAction;
import ru.ItzDarth.DarthRPG.api.inventory.CustomHolder;
import ru.ItzDarth.DarthRPG.api.inventory.Icon;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getView().getTopInventory().getHolder() instanceof CustomHolder) {
	        e.setCancelled(true);
	        if(e.getWhoClicked() instanceof Player) {
	            Player player = (Player) e.getWhoClicked();
	            ItemStack itemStack = e.getCurrentItem();
				
	            if (itemStack == null || itemStack.getType() == Material.AIR) return;

	            CustomHolder customHolder = (CustomHolder) e.getView().getTopInventory().getHolder();
				
	            Icon icon = customHolder.getIcon(e.getRawSlot());
	            if (icon == null) return;
	            for(ClickAction clickAction : icon.getClickActions()) {
	                clickAction.execute(player);
	            }
	        }
	    }
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if(e.getView().getTopInventory().getHolder() instanceof CustomHolder) {
			if(e.getPlayer() instanceof Player) {
				CustomHolder customHolder = (CustomHolder) e.getView().getTopInventory().getHolder();
				if(customHolder.getCancelClose() == true) {
					e.getPlayer().openInventory(customHolder.getInventory());
				}
			}
		}
	}
	
}
