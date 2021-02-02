package ru.ItzDarth.DarthRPG.utils;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils {
	
	public static String commas(double value) {
		return NumberFormat.getNumberInstance(Locale.US).format(value);
	}
	
	public static Location getCenter(Location loc) {
	    return new Location(loc.getWorld(),
	        getRelativeCoord(loc.getBlockX()),
	        loc.getBlockY(),
	        getRelativeCoord(loc.getBlockZ()));
	}
	
	public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
         if (0 <= rotation && rotation < 22.5) {
            return "W";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "WN";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "N";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "NE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "E";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "ES";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "S";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "SW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "W";
        } else {
            return null;
        }
    }
	
	private static double getRelativeCoord(int i) {
	    double d = i;
	    d = d < 0 ? d - .5 : d + .5;
	    return d;
	}
	
}
