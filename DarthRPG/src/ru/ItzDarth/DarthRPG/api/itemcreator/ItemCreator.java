package ru.ItzDarth.DarthRPG.api.itemcreator;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import ru.ItzDarth.DarthRPG.api.reflection.ReflectionAPI;

@SuppressWarnings("deprecation")
public class ItemCreator {
	
	private ItemStack itemStack;
	
	public ItemCreator() {
		itemStack = new ItemStack(Material.AIR);
	}
	
	public ItemCreator(Material material) {
		itemStack = new ItemStack(material);
	}
	
	public ItemCreator(int id) {
		itemStack = new ItemStack(id);
	}
	
	public ItemCreator(int id, int data) {
		itemStack = new ItemStack(id, 1, (short) data);
	}
	
	/**
	 * Устанавливает материал предмету
	 *
	 * @param   material  Материал
	 */
	public ItemCreator setMaterial(Material material) {
		itemStack = new ItemStack(material);
		return this;
	}
	/**
	 * Устанавливает материал предмету
	 *
	 * @param   id  ID предмета
	 */
	public ItemCreator setMaterial(int id) {
		itemStack = new ItemStack(id);
		return this;
	}
	/**
	 * Устанавливает материал предмету
	 *
	 * @param   id    ID предмета
	 * @param   data  Data предмета
	 */
	public ItemCreator setMaterial(int id, int data) {
		itemStack = new ItemStack(id, 1, (short) data);
		return this;
	}
	
	/**
	 * Устанавливает прочность предмету
	 *
	 * @param   durability  Прочность
	 */
	public ItemCreator setDurability(int durability) {
		itemStack.setDurability((short) durability);
		return this;
	}
	
	/**
	 * Устанавливает неразрушимость предмету
	 *
	 * @param   flag  Включить или выключить
	 */
	public ItemCreator setUnbreakable(boolean flag) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.setUnbreakable(flag);
		itemStack.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Добавляет зачарования на предмет
	 *
	 * @param   enchantment  Зачарование
	 * @param   level        Уровень зачарования
	 */
	public ItemCreator addEnchantment(Enchantment enchantment, int level) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.addEnchant(enchantment, level, true);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * Добавляет флаг на предмет
	 *
	 * @param   flag  Флаг
	 */
	public ItemCreator addItemFlag(ItemFlag flag) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.addItemFlags(flag);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * Устанавливает имя на предмет
	 *
	 * @param   name  Имя предмета
	 */
	public ItemCreator setName(String name) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.setDisplayName(name);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * Устанавливает описание на предмет
	 *
	 * @param   lore  Описание предмета
	 */
	public ItemCreator setLore(List<String> lore) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.setLore(lore);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	/**
	 * Устанавливает описание на предмет
	 *
	 * @param   lore  Описание предмета
	 */
	public ItemCreator setLore(String... lore) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.setLore(Arrays.asList(lore));
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * Устанавливает количество предметов
	 *
	 * @param   amount  Количество предметов
	 */
	public ItemCreator setAmount(int amount) {
	    itemStack.setAmount(amount);
	    return this;
	}
	
	/**
	 * Устанавливает на кожаную броню цвет
	 *
	 * @param   color  Цвет брони
	 */
	public ItemCreator setLeatherColor(Color color) {
		switch(itemStack.getType()) {
			case LEATHER_HELMET:
			case LEATHER_CHESTPLATE:
			case LEATHER_LEGGINGS:
			case LEATHER_BOOTS: {
				LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
				meta.setColor(color);
				itemStack.setItemMeta(meta);
				break;
			}
			default: {
				break;
			}
		}
	    return this;
	}
	
	/**
	 * Устанавливает на голову игрока скин по нику
	 *
	 * @param   name  Ник игрока
	 */
	public ItemCreator setPlayerSkull(String name) {
		if(itemStack.getType() == Material.SKULL_ITEM && itemStack.getDurability() == 3) {
			SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
			meta.setOwner(name == null ? "ItzDarth" : name);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS );
			itemStack.setItemMeta(meta);
		}
	    return this;
	}
	
	/**
	 * Устанавливает на голову игрока скин по текстуре
	 *
	 * @param   texture  Текстура
	 */
	public ItemCreator setTextureSkull(String texture) {
		if(itemStack.getType() == Material.SKULL_ITEM && itemStack.getDurability() == 3) {
			SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS );
			
			GameProfile profile = new GameProfile(UUID.randomUUID(), "ItzDarth");
			profile.getProperties().put("textures", new Property("textures", texture));
			ReflectionAPI.setField(meta, "profile", profile);
			
			itemStack.setItemMeta(meta);
		}
	    return this;
	}
	
	/**
	 * Собирает предмет и возращает <code>ItemStack</code>
	 */
	public ItemStack build() {
		return itemStack;
	}
	
}
