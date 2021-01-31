package ru.ItzDarth.DarthRPG.menus.start;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.inventory.ClickAction;
import ru.ItzDarth.DarthRPG.api.inventory.CustomHolder;
import ru.ItzDarth.DarthRPG.api.inventory.Icon;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;
import ru.ItzDarth.DarthRPG.api.language.Language;

public class SelectLanguageGUI {
	
	public SelectLanguageGUI(Player p) {
		RPGPlayer rp = RPGApi.getRPGPlayer(p);
		CustomHolder inv = new CustomHolder(27, "Choose a language", true);
		
		inv.setIcon(11, new Icon(
				new ItemCreator(Material.SKULL_ITEM, 3)
				.setTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZlYWZlZjk4MGQ2MTE3ZGFiZTg5ODJhYzRiNDUwOTg4N2UyYzQ2MjFmNmE4ZmU1YzliNzM1YTgzZDc3NWFkIn19fQ==")
				.setName(Language.RUSSIAN.getMessage("choose-language-title")).setLore(Language.RUSSIAN.getListMessage("choose-language-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				rp.LANGUAGE = Language.RUSSIAN;
				new ChooseClassGUI(player);
			}
		}));
		
		inv.setIcon(15, new Icon(
				new ItemCreator(Material.SKULL_ITEM, 3)
					.setTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNhYzk3NzRkYTEyMTcyNDg1MzJjZTE0N2Y3ODMxZjY3YTEyZmRjY2ExY2YwY2I0YjM4NDhkZTZiYzk0YjQifX19")
					.setName(Language.ENGLISH.getMessage("choose-language-title")).setLore(Language.ENGLISH.getListMessage("choose-language-lore")).build()
				).addClickAction(new ClickAction() {
			public void execute(Player player) {
				rp.LANGUAGE = Language.ENGLISH;
				new ChooseClassGUI(player);
			}
		}));
		
		p.openInventory(inv.getInventory());
	}
	
}
