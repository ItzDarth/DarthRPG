package ru.ItzDarth.DarthRPG.api.banmanager;

import org.bukkit.entity.Player;

public class BanManager {
	
	public static void kick(Player p, String message) {
		p.kickPlayer(message);
	}
	
}
