package ru.ItzDarth.DarthRPG.api.darthrpg;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationAPI {
	
	public static Location LOADING_LOCATION;
	
	public static void init() {
		LOADING_LOCATION = new Location(Bukkit.getWorld("world"), 8.5, 5, 8.5, 0, 0);
	}
	
}
