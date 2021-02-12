package ru.ItzDarth.DarthRPG.api.darthrpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.ItemRegister;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.DamageType;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem.ItemRarity;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;

public enum ItemAPI {
	
	//
	// Нейтральный - P
	// Земляной  - E
	// Электрический - T
	// Водяной - W
	// Огненный - F
	// Воздушный - A
	//
	
	WOODEN_SWORD(new ItemCreator(Material.WOOD_SWORD),                            "woodensword",      zClass.WARRIOR,   ItemRarity.NORMAL, new String[][] {{"P", "7"}}, new String[][] {}),
	WOODEN_BOW(new ItemCreator(Material.BOW),                                     "woodenbow",        zClass.ARCHER,    ItemRarity.NORMAL, new String[][] {{"P", "7"}}, new String[][] {}),
	WOODEN_STAFF(new ItemCreator(Material.STONE_SWORD).setDurability(1),          "woodenstaff",      zClass.MAGE,      ItemRarity.NORMAL, new String[][] {{"P", "6"}}, new String[][] {}),
	WOODEN_RELIC(new ItemCreator(Material.STONE_AXE).setDurability(1),            "woodenrelic",      zClass.SHAMAN,    ItemRarity.NORMAL, new String[][] {{"P", "6"}}, new String[][] {}),
	WOODEN_KNIFE(new ItemCreator(Material.WOOD_SWORD).setDurability(1),           "woodenknife",      zClass.ASSASIN,   ItemRarity.NORMAL, new String[][] {{"P", "6"}}, new String[][] {});
	
	private ItemCreator item;
	private String key;
	private ItemRegister itemInfo;
	private zClass CLASS;
	private ItemRarity rarity;
	
	ItemAPI(ItemCreator item, String key, zClass CLASS, ItemRarity rarity, String[][] damages, String[][] indetifications) {
		this.item = item.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true);
		this.key = "item-"+key+"-name";
		this.itemInfo = new ItemRegister(damages, indetifications);
		this.CLASS = CLASS;
		this.rarity = rarity;
	}
	
	public ItemStack getItem() {
		return item.build();
	}
	
	public ItemStack generateItem(RPGPlayer rp, int level) {
		RPGItem rpgItem = new RPGItem(rp, item, level, CLASS, rarity, key);
		if(itemInfo.PHYSICAL > 0) rpgItem.setDamage(DamageType.PHYSICAL, itemInfo.PHYSICAL);
		if(itemInfo.EARTH > 0) rpgItem.setDamage(DamageType.EARTH, itemInfo.EARTH);
		if(itemInfo.THUNDER > 0) rpgItem.setDamage(DamageType.THUNDER, itemInfo.THUNDER);
		if(itemInfo.WATER > 0) rpgItem.setDamage(DamageType.WATER, itemInfo.WATER);
		if(itemInfo.FIRE > 0) rpgItem.setDamage(DamageType.FIRE, itemInfo.FIRE);
		if(itemInfo.AIR > 0) rpgItem.setDamage(DamageType.AIR, itemInfo.AIR);
		
		rpgItem.setLoreLevelAndClass();
		
		return rpgItem.build(itemInfo.INDETIFICATIONS);
	}
	
	
}
