package ru.ItzDarth.DarthRPG.api.reflection;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.Packet;

public class ReflectionAPI {
	
	private static String version = "need";
	
	public static Class<?> getNMSClass(String name) {
		if(version.equals("need")) {
			version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		}
		try {
			return Class.forName("net.minecraft.server."+version+"."+name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public static void setField(Object object, String field, Object fieldValue) {
		Field f = null;
		Class<?> clazz = object.getClass();
		
		try {
			f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			f.set(object, fieldValue);
		}
		catch(IllegalAccessException|NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			f.setAccessible(false);
		} 
	}

	public static void sendPacket(Player p, Packet<?> packet) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void sendPacket(Player p, Object packet) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet);
	}
	
}
