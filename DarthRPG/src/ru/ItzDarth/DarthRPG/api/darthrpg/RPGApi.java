package ru.ItzDarth.DarthRPG.api.darthrpg;

import java.util.HashMap;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;

public class RPGApi {
	
	public static HashMap<Player, RPGPlayer> RPGPLAYERS = new HashMap<Player, RPGPlayer>();
	
	public static RPGPlayer addRPGPlayer(Player p) {
		return RPGPLAYERS.put(p, new RPGPlayer(p));
	}
	
	public static RPGPlayer removeRPGPlayer(Player p) {
		return RPGPLAYERS.remove(p);
	}
	
	public static RPGPlayer getRPGPlayer(Player p) {
		return RPGPLAYERS.get(p);
	}
	
}
