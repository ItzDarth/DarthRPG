package ru.ItzDarth.DarthRPG.api.darthrpg.items;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.ItzDarth.DarthRPG.api.darthrpg.items.build.BuildAPI;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class BuildItem {
	
	private ItemStack item;
	
	public BuildItem(ItemStack item) {
		this.item = item;
	}
	
	public boolean check(Player p, ItemStack hand, Block block) {
		boolean result = false;
		
		if(hand.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
			result = true;
			World world = p.getWorld();
			ArmorStand stand = world.spawn(Utils.getCenter(block.getLocation()), ArmorStand.class);
			stand.setGravity(false);
			stand.setInvulnerable(true);
			stand.setVisible(false);
			stand.getEquipment().setHelmet(item);
			BuildAPI.addStand(stand);
		}
		
		return result;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
}
