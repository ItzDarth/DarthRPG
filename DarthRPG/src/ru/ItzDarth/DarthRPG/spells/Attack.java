package ru.ItzDarth.DarthRPG.spells;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;

public interface Attack {
	
	public zClass getCLASS();
	
	public void run(RPGPlayer rp, ItemStack item, NBTTagCompound tag);
	
}
