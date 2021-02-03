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
	
	public ItemCreator(Material material, int data) {
		itemStack = new ItemStack(material, 1, (short) data);
	}
	
	public ItemCreator(int id) {
		itemStack = new ItemStack(id);
	}
	
	public ItemCreator(int id, int data) {
		itemStack = new ItemStack(id, 1, (short) data);
	}
	
	public ItemCreator(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	/**
	 * ������������� �������� ��������
	 *
	 * @param   material  ��������
	 */
	public ItemCreator setMaterial(Material material) {
		itemStack = new ItemStack(material);
		return this;
	}
	/**
	 * ������������� �������� ��������
	 *
	 * @param   id  ID ��������
	 */
	public ItemCreator setMaterial(int id) {
		itemStack = new ItemStack(id);
		return this;
	}
	/**
	 * ������������� �������� ��������
	 *
	 * @param   id    ID ��������
	 * @param   data  Data ��������
	 */
	public ItemCreator setMaterial(int id, int data) {
		itemStack = new ItemStack(id, 1, (short) data);
		return this;
	}
	
	/**
	 * ������������� ��������� ��������
	 *
	 * @param   durability  ���������
	 */
	public ItemCreator setDurability(int durability) {
		itemStack.setDurability((short) durability);
		return this;
	}
	
	/**
	 * ������������� �������������� ��������
	 *
	 * @param   flag  �������� ��� ���������
	 */
	public ItemCreator setUnbreakable(boolean flag) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.setUnbreakable(flag);
		itemStack.setItemMeta(meta);
		return this;
	}
	
	/**
	 * ��������� ����������� �� �������
	 *
	 * @param   enchantment  �����������
	 * @param   level        ������� �����������
	 */
	public ItemCreator addEnchantment(Enchantment enchantment, int level) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.addEnchant(enchantment, level, true);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * ��������� ���� �� �������
	 *
	 * @param   flag  ����
	 */
	public ItemCreator addItemFlag(ItemFlag flag) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.addItemFlags(flag);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * ������������� ��� �� �������
	 *
	 * @param   name  ��� ��������
	 */
	public ItemCreator setName(String name) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.setDisplayName(name);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * ������������� �������� �� �������
	 *
	 * @param   lore  �������� ��������
	 */
	public ItemCreator setLore(List<String> lore) {
		ItemMeta meta = itemStack.getItemMeta();
	    meta.setLore(lore);
	    itemStack.setItemMeta(meta);
	    return this;
	}
	/**
	 * ������������� �������� �� �������
	 *
	 * @param   lore  �������� ��������
	 */
	public ItemCreator setLore(String... lore) {
		ItemMeta meta = itemStack.getItemMeta();
		meta.setLore(Arrays.asList(lore));
	    itemStack.setItemMeta(meta);
	    return this;
	}
	
	/**
	 * ������������� ���������� ���������
	 *
	 * @param   amount  ���������� ���������
	 */
	public ItemCreator setAmount(int amount) {
	    itemStack.setAmount(amount);
	    return this;
	}
	
	/**
	 * ������������� �� ������� ����� ����
	 *
	 * @param   color  ���� �����
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
	 * ������������� �� ������ ������ ���� �� ����
	 *
	 * @param   name  ��� ������
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
	 * ������������� �� ������ ������ ���� �� ��������
	 *
	 * @param   texture  ��������
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
	 * �������� ������� � ��������� <code>ItemStack</code>
	 */
	public ItemStack build() {
		return itemStack;
	}
	
}
