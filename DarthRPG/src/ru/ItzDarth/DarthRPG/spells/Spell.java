package ru.ItzDarth.DarthRPG.spells;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;

public interface Spell {
	
	public String getKeyName();
	
	public int getCost_I();
	public int getCost_II();
	public int getCost_III();
	
	public void runGradle_I(RPGPlayer rp, NBTTagCompound tag);
	public void runGradle_II(RPGPlayer rp, NBTTagCompound tag);
	public void runGradle_III(RPGPlayer rp, NBTTagCompound tag);
	
}
