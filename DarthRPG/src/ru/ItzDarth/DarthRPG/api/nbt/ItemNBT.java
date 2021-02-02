package ru.ItzDarth.DarthRPG.api.nbt;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class ItemNBT {
	
	private net.minecraft.server.v1_12_R1.ItemStack nmsItem;
	
	public ItemNBT(ItemStack item) {
		nmsItem = CraftItemStack.asNMSCopy(item);
	}
	
	public NBTTagCompound getTag() {
		return nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
	}
	
	public void setTag(NBTTagCompound tag) {
		nmsItem.setTag(tag);
	}
	
	public ItemStack getItemStack() {
		return CraftItemStack.asBukkitCopy(nmsItem);
	}
   
}
