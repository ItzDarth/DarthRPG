package ru.ItzDarth.DarthRPG.api.darthrpg;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;

public class RPGApi {
	
	public static RPGPlayer getRPGPlayer(Player p) {
		return (RPGPlayer) p.getMetadata("info").get(0).value();
	}
	
}
