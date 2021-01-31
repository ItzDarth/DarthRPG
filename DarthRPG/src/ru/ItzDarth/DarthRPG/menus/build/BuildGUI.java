package ru.ItzDarth.DarthRPG.menus.build;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.inventory.CustomHolder;

public class BuildGUI {
	
	public BuildGUI(Player p) {
		CustomHolder inv = new CustomHolder(54, "Меню строителя");
		
		p.openInventory(inv.getInventory());
	}
	
}
