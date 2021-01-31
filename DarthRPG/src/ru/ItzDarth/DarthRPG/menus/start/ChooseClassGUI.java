package ru.ItzDarth.DarthRPG.menus.start;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.ItemAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.inventory.ClickAction;
import ru.ItzDarth.DarthRPG.api.inventory.CustomHolder;
import ru.ItzDarth.DarthRPG.api.inventory.Icon;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;

public class ChooseClassGUI {
	
	public ChooseClassGUI(Player p) {
		RPGPlayer rp = RPGApi.getRPGPlayer(p);
		CustomHolder inv = new CustomHolder(27, rp.LANGUAGE.getMessage("choose-class-title"), true);
		
		inv.setIcon(11, new Icon(
				new ItemCreator(ItemAPI.WOODEN_SWORD.getItem()).setName(rp.LANGUAGE.getMessage("choose-class-warrior-title")).setLore(rp.LANGUAGE.getListMessage("choose-class-warrior-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				selectClass(player, inv, rp, zClass.WARRIOR);
			}
		}));
		inv.setIcon(12, new Icon(
				new ItemCreator(ItemAPI.WOODEN_BOW.getItem()).setName(rp.LANGUAGE.getMessage("choose-class-archer-title")).setLore(rp.LANGUAGE.getListMessage("choose-class-archer-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				selectClass(player, inv, rp, zClass.ARCHER);
			}
		}));
		inv.setIcon(13, new Icon(
				new ItemCreator(ItemAPI.WOODEN_STAFF.getItem()).setName(rp.LANGUAGE.getMessage("choose-class-mage-title")).setLore(rp.LANGUAGE.getListMessage("choose-class-mage-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				selectClass(player, inv, rp, zClass.MAGE);
			}
		}));
		inv.setIcon(14, new Icon(
				new ItemCreator(ItemAPI.WOODEN_BATTLEAXE.getItem()).setName(rp.LANGUAGE.getMessage("choose-class-berserk-title")).setLore(rp.LANGUAGE.getListMessage("choose-class-berserk-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				selectClass(player, inv, rp, zClass.BERSERK);
			}
		}));
		inv.setIcon(15, new Icon(
				new ItemCreator(ItemAPI.WOODEN_ARCHEMAGE_STAFF.getItem()).setName(rp.LANGUAGE.getMessage("choose-class-archemage-title")).setLore(rp.LANGUAGE.getListMessage("choose-class-archemage-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				selectClass(player, inv, rp, zClass.ARCHEMAGE);
			}
		}));
		
		p.openInventory(inv.getInventory());
	}
	
	private void selectClass(Player player, CustomHolder inv, RPGPlayer rp, zClass clas) {
		rp.CLASS = clas;
		inv.setCancelClose(false);
		player.closeInventory();
		rp.loadAndStart(true);
	}
	
}
