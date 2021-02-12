package ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.indetifications.Indetification;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class RPGItem {
	
	public static ItemStack updateInfoItem(RPGPlayer rp, ItemStack item, NBTTagCompound tag) {
		int level = tag.getInt("level");
		zClass Class = zClass.valueOf(tag.getString("class"));
		ItemRarity rarity = ItemRarity.valueOf(tag.getString("rarity"));
		
		int physical = tag.getInt(DamageType.PHYSICAL.name().toLowerCase());
		int earth = tag.getInt(DamageType.EARTH.name().toLowerCase());
		int thunder = tag.getInt(DamageType.THUNDER.name().toLowerCase());
		int water = tag.getInt(DamageType.WATER.name().toLowerCase());
		int fire = tag.getInt(DamageType.FIRE.name().toLowerCase());
		int air = tag.getInt(DamageType.AIR.name().toLowerCase());
		
		List<String> lores = new ArrayList<String>();
		lores.add("");
		if(physical > 0) lores.add(DamageType.PHYSICAL.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+DamageType.PHYSICAL.name().toLowerCase()).replace("{dmg}", Utils.commas(physical)));
		if(earth > 0) lores.add(DamageType.EARTH.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+DamageType.EARTH.name().toLowerCase()).replace("{dmg}", Utils.commas(earth)));
		if(thunder > 0) lores.add(DamageType.THUNDER.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+DamageType.THUNDER.name().toLowerCase()).replace("{dmg}", Utils.commas(thunder)));
		if(water > 0) lores.add(DamageType.WATER.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+DamageType.WATER.name().toLowerCase()).replace("{dmg}", Utils.commas(water)));
		if(fire > 0) lores.add(DamageType.FIRE.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+DamageType.FIRE.name().toLowerCase()).replace("{dmg}", Utils.commas(fire)));
		if(air > 0) lores.add(DamageType.AIR.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+DamageType.AIR.name().toLowerCase()).replace("{dmg}", Utils.commas(air)));
		
		lores.add("");
		lores.add(rp.LANGUAGE.getMessage("item-lore-lvlmin-"+(rp.level >= level ? "y" : "n")).replace("{lvl}", level+""));
		lores.add(rp.LANGUAGE.getMessage("item-lore-classneed-"+(rp.CLASS == Class ? "y" : "n")).replace("{class}", rp.LANGUAGE.getMessage("class-name-"+Class.name().toLowerCase())));
		lores.add("");
		
		NBTTagList indetifications = tag.getList("indetifications", 10);
		
		for(int i = 0; i < indetifications.size(); i++) {
			NBTTagCompound list = indetifications.get(i);
			String name = list.getString("a");
			int value = list.getInt("b");
			
			lores.add(rp.LANGUAGE.getMessage("item-lore-indetification-"+name).replace("{value}", (value > 0 ? "§a" : "§c")+value));
		}
		if(indetifications.size() > 0) lores.add("");
		// ДОДЕЛАТЬ
		lores.add(rp.LANGUAGE.getMessage("item-lore-slots-powder").replace("{current}", "0").replace("{max}", "4"));
		lores.add(rarity.STARTWITH+rp.LANGUAGE.getMessage("item-lore-rarity-"+rarity.name().toLowerCase()));
		
		return new ItemCreator(item).setLore(lores).build();
	}
	
	public static ItemStack updateInfoItem(RPGPlayer rp, ItemStack item) {
		NBTTagCompound tag = new ItemNBT(item).getTag();
		return updateInfoItem(rp, item, tag);
	}
	
	public ItemNBT NBT;
	public NBTTagCompound TAG;
	public List<String> lores = new ArrayList<String>();
	private int level;
	private zClass CLASS;
	private RPGPlayer rp;
	private String key;
	private ItemRarity rarity;
	
	public RPGItem(RPGPlayer rp, ItemCreator itemCreator, int level, zClass CLASS, ItemRarity rarity, String key) {
		this.rp = rp;
		this.key = key;
		this.rarity = rarity;
		NBT = new ItemNBT(itemCreator.build());
		TAG = NBT.getTag();
		
		TAG.setBoolean("weapon", true);
		TAG.setInt("level", level);
		TAG.setString("class", CLASS.name());
		TAG.setString("rarity", rarity.name());
		this.level = level;
		this.CLASS = CLASS;
		
		lores.add("");
		
		//
		// DAMAGE TYPES: []
		//
		// LVL min
		// CLASS need
		//
		// INDETIFICATIONS
		//
		// SLOTS
		// TYPE
	}
	
	private List<DamageType> usesTypes = new ArrayList<DamageType>();
	
	public void setDamage(DamageType type, int damage) {
		TAG.setInt(type.name().toLowerCase(), damage);
		usesTypes.add(type);
		lores.add(type.STARTWITH+" "+rp.LANGUAGE.getMessage("item-lore-damagetype-"+type.name().toLowerCase()).replace("{dmg}", Utils.commas(damage)));
	}
	
	public void setLoreLevelAndClass() {
		for(DamageType type : DamageType.values()) {
			if(usesTypes.contains(type)) continue;
			TAG.setInt(type.name().toLowerCase(), 0);
		}
		lores.add("");
		lores.add(rp.LANGUAGE.getMessage("item-lore-lvlmin-"+(rp.level >= level ? "y" : "n")).replace("{lvl}", level+""));
		lores.add(rp.LANGUAGE.getMessage("item-lore-classneed-"+(rp.CLASS == CLASS ? "y" : "n")).replace("{class}", rp.LANGUAGE.getMessage("class-name-"+CLASS.name().toLowerCase())));
		lores.add("");
	}
	
	private NBTTagList getIndetifications(List<Indetification> indetifications) {
		NBTTagList tag = new NBTTagList();
		for(Indetification indetification : indetifications) {
			NBTTagCompound data = new NBTTagCompound();
			data.setString("a", indetification.NAME);
			data.setInt("b", indetification.VALUE);
			tag.add(data);
			lores.add(rp.LANGUAGE.getMessage("item-lore-indetification-"+indetification.NAME).replace("{value}", (indetification.VALUE > 0 ? "§a" : "§c")+indetification.VALUE));
		}
		if(indetifications.size() > 0) lores.add("");
		// ДОДЕЛАТЬ
		lores.add(rp.LANGUAGE.getMessage("item-lore-slots-powder").replace("{current}", "0").replace("{max}", "4"));
		lores.add(rarity.STARTWITH+rp.LANGUAGE.getMessage("item-lore-rarity-"+rarity.name().toLowerCase()));
		
		return tag;
	}
	
	public ItemStack build(List<Indetification> indetifications) {
		TAG.set("indetifications", getIndetifications(indetifications));
		
		NBT.setTag(TAG);
		return new ItemCreator(NBT.getItemStack()).setName(rp.LANGUAGE.getMessage(key)).setLore(lores).build();
	}
	
	public enum DamageType {
		PHYSICAL("§6✤", "§6-{x} ✤"),
		EARTH("§2✤", "§2-{x} ✤"),
		THUNDER("§e✦", "§e-{x} ✦"),
		WATER("§b❉", "§b-{x} ❉"),
		FIRE("§c✹", "§c-{x} ✹"),
		AIR("§f❋", "§f-{x} ❋");
		
		public String STARTWITH;
		public String STARTWITHDAMAGE;
		
		DamageType(String startwith, String startwithdamage) {
			this.STARTWITH = startwith;
			this.STARTWITHDAMAGE = startwithdamage;
		}
	}
	
	public enum ItemRarity {
		NORMAL("§f"),      // WOODEN
		UNIQUE("§e"),      // STONE
		RARE("§d"),        // IRON
		LEGENDARY("§6"),   // GOLDEN
		FABLED("§c"),      // DIAMOND
		MYTHIC("§5");      // TITANIT
		
		public String STARTWITH;
		
		ItemRarity(String startwith) {
			this.STARTWITH = startwith;
		}
	}
	
}
