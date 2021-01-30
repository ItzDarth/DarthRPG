package ru.ItzDarth.DarthRPG.api.darthrpg;

import java.util.HashMap;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;

public class RPGApi {
	
	private static HashMap<Player, RPGPlayer> RPGPlayers = new HashMap<Player, RPGPlayer>();
	
	public static RPGPlayer addRPGPlayer(Player p) {
		return RPGPlayers.put(p, new RPGPlayer(p));
	}
	
	public static RPGPlayer removeRPGPlayer(Player p) {
		return RPGPlayers.remove(p);
	}
	
	public static RPGPlayer getRPGPlayer(Player p) {
		return RPGPlayers.get(p);
	}
	
}
