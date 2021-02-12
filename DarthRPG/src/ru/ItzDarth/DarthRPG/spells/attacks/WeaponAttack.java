package ru.ItzDarth.DarthRPG.spells.attacks;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.spells.Attack;

public class WeaponAttack implements Attack {
	
	public zClass getCLASS() {
		return zClass.WARRIOR;
	}
	
	public void run(RPGPlayer rp, ItemStack item, NBTTagCompound tag) {
		rp.PLAYER.setCooldown(item.getType(), 15);
	}
	
}
