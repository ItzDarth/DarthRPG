package ru.ItzDarth.DarthRPG;

import org.bukkit.plugin.java.JavaPlugin;

import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.language.LanguageManager;
import ru.ItzDarth.DarthRPG.api.mysql.MySQL;
import ru.ItzDarth.DarthRPG.api.mysql.MySQLAPI;
import ru.ItzDarth.DarthRPG.api.npc.NpcAPI;
import ru.ItzDarth.DarthRPG.listeners.JoinListener;
import ru.ItzDarth.DarthRPG.listeners.QuitListener;

public class DarthRPG extends JavaPlugin {
	
	public static DarthRPG INSTANCE;
	public static MySQL MYSQL;
	
	public void onEnable() {
		INSTANCE = this;
		
		// Инициализация всех api
		NpcAPI.onEnable(this);
		
		LanguageManager.loadSite(Language.RUSSIAN);
		LanguageManager.loadSite(Language.ENGLISH);
		
		LocationAPI.init();
		
		MYSQL = MySQLAPI.connect("localhost", 3306, "darthrpg", "root", "");
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new QuitListener(), this);
	}
	
	public void onDisable() {
		NpcAPI.onDisable();
	}
	
}
