package ru.ItzDarth.DarthRPG.api.darthrpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;

public enum ItemAPI {
	
	WOODEN_SWORD(new ItemCreator(Material.WOOD_SWORD), "item-woodensword"),
	WOODEN_BOW(new ItemCreator(Material.BOW), "item-woodenbow"),
	WOODEN_STAFF(new ItemCreator(Material.STONE_SWORD).setDurability(Material.STONE_SWORD.getMaxDurability()-1), "item-woodenstaff"),
	WOODEN_BATTLEAXE(new ItemCreator(Material.STONE_AXE).setDurability(Material.STONE_SWORD.getMaxDurability()-1), "item-woodenbattleaxe"),
	WOODEN_ARCHEMAGE_STAFF(new ItemCreator(Material.WOOD_SWORD).setDurability(Material.STONE_SWORD.getMaxDurability()-1), "item-woodenarchestaff");
	
	private ItemCreator item;
	
	ItemAPI(ItemCreator item, String key) {
		this.item = item;
		item.setName(key+"-name");
	}
	
	public ItemStack getItem() {
		return item.build();
	}
	
}
