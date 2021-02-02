package ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.indetifications.Indetification;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class RPGItem {
	
	public ItemNBT NBT;
	public NBTTagCompound TAG;
	public List<String> lores = new ArrayList<String>();
	private int level;
	private zClass CLASS;
	private RPGPlayer rp;
	private ItemRarity rarity;
	
	public RPGItem(RPGPlayer rp, ItemCreator itemCreator, int level, zClass CLASS, ItemRarity rarity, String key) {
		this.rp = rp;
		this.rarity = rarity;
		itemCreator.setName(rp.LANGUAGE.getMessage(key));
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
	
	public void setDamage(DamageType type, int damage) {
		TAG.setInt(type.name().toLowerCase(), damage);
		lores.add(type.STARTWITH+rp.LANGUAGE.getMessage("item-lore-damagetype-"+type.name().toLowerCase()).replace("{dmg}", Utils.commas(damage)));
	}
	
	public void setLoreLevelAndClass() {
		lores.add(rp.LANGUAGE.getMessage("item-lore-lvlmin-"+(rp.level >= level ? "y" : "n")).replace("{lvl}", level+""));
		lores.add(rp.LANGUAGE.getMessage("item-lore-classneed-"+(rp.CLASS == CLASS ? "y" : "n")).replace("{class}", rp.LANGUAGE.getMessage("class-name-"+CLASS.name().toLowerCase())));
		lores.add("");
	}
	
	private NBTTagCompound getIndetifications(List<Indetification> indetifications) {
		NBTTagCompound tag = new NBTTagCompound();
		for(Indetification indetification : indetifications) {
			tag.setInt(indetification.NAME, indetification.VALUE);
			lores.add(rp.LANGUAGE.getMessage("item-lore-indetification-"+indetification.NAME).replace("{value}", (indetification.VALUE > 0 ? "§a" : "§c")+indetification.VALUE));
		}
		lores.add("");
		// ДОДЕЛАТЬ
		lores.add(rp.LANGUAGE.getMessage("item-lore-slots-powder").replace("{current}", "0").replace("{max}", "4"));
		//
		lores.add(rarity.STARTWITH+rp.LANGUAGE.getMessage("item-lore-rarity-"+rarity.name().toLowerCase()));
		
		return tag;
	}
	
	public ItemStack build(List<Indetification> indetifications) {
		TAG.set("indetifications", getIndetifications(indetifications));
		
		NBT.setTag(TAG);
		return NBT.getItemStack();
	}
	
	public enum DamageType {
		PHYSICAL("§6✤"),
		EARTH("§2✤"),
		THUNDER("§e✦"),
		WATER("§b❉"),
		FIRE("§c✹"),
		AIR("§f❋");
		
		public String STARTWITH;
		
		DamageType(String startwith) {
			this.STARTWITH = startwith;
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
