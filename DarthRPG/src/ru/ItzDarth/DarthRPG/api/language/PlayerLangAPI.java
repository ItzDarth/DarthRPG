package ru.ItzDarth.DarthRPG.api.language;

import org.bukkit.entity.Player;

public class PlayerLangAPI {
	
	@SuppressWarnings("deprecation")
	public static Language getLanguage(Player p) {
		String locale = p.spigot().getLocale();
		System.out.println(p.getName()+" - "+locale);
		switch(locale) {
			case "ru_ru": {
				return Language.RUSSIAN;
			}
			default: {
				return Language.ENGLISH;
			}
		}
	}
	
}
