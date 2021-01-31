package ru.ItzDarth.DarthRPG;

import org.bukkit.plugin.java.JavaPlugin;

import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.language.LanguageManager;
import ru.ItzDarth.DarthRPG.api.mysql.MySQL;
import ru.ItzDarth.DarthRPG.api.mysql.MySQLAPI;
import ru.ItzDarth.DarthRPG.api.npc.NpcAPI;
import ru.ItzDarth.DarthRPG.listeners.InventoryListener;
import ru.ItzDarth.DarthRPG.listeners.JoinListener;
import ru.ItzDarth.DarthRPG.listeners.QuitListener;
import ru.ItzDarth.DarthRPG.listeners.ResourcePackListener;

public class DarthRPG extends JavaPlugin {
	
	public static DarthRPG INSTANCE;
	public static MySQL MYSQL;
	
	public void onEnable() {
		INSTANCE = this;
		
		// ������������� ���� api
		NpcAPI.onEnable(this);
		
		LanguageManager.loadSite(Language.RUSSIAN);
		LanguageManager.loadSite(Language.ENGLISH);
		
		LocationAPI.init();
		
		MYSQL = MySQLAPI.connect("localhost", 3306, "darthrpg", "root", "");
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new QuitListener(), this);
		getServer().getPluginManager().registerEvents(new ResourcePackListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
	}
	
	public void onDisable() {
		NpcAPI.onDisable();
	}
	
}
