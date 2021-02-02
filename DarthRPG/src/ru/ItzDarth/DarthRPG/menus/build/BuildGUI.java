package ru.ItzDarth.DarthRPG.menus.build;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.items.BuildItem;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.build.BuildAPI;
import ru.ItzDarth.DarthRPG.api.inventory.ClickAction;
import ru.ItzDarth.DarthRPG.api.inventory.CustomHolder;
import ru.ItzDarth.DarthRPG.api.inventory.Icon;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;

public class BuildGUI {
	
	public BuildGUI(Player p) {
		CustomHolder inv = new CustomHolder(54, "Меню строителя");
		
		int i = 0;
		for(BuildItem item : BuildAPI.ITEMS.values()) {
			inv.setIcon(i, new Icon(item.getItem()).addClickAction(new ClickAction() {
				public void execute(Player player) {
					player.closeInventory();
					player.getInventory().addItem(item.getItem());
				}
			}));
			i++;
		}
		
		inv.setIcon(i, new Icon(new ItemCreator(Material.BLAZE_ROD).setDurability(1).setName("Повернуть кастомный блок").build()).addClickAction(new ClickAction() {
				public void execute(Player player) {
					player.closeInventory();
					player.getInventory().addItem(new ItemCreator(Material.BLAZE_ROD).setDurability(1).setName("Повернуть кастомный блок").build());
				}
			}));
		i++;
		inv.setIcon(i, new Icon(new ItemCreator(Material.BLAZE_POWDER).setDurability(1).setName("Удалить кастомный блок").build()).addClickAction(new ClickAction() {
			public void execute(Player player) {
				player.closeInventory();
				player.getInventory().addItem(new ItemCreator(Material.BLAZE_POWDER).setDurability(1).setName("Удалить кастомный блок").build());
			}
		}));
		
		p.openInventory(inv.getInventory());
	}
	
}
