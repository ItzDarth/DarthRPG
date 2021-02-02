package ru.ItzDarth.DarthRPG;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.build.BuildAPI;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.language.LanguageManager;
import ru.ItzDarth.DarthRPG.api.mysql.MySQL;
import ru.ItzDarth.DarthRPG.api.mysql.MySQLAPI;
import ru.ItzDarth.DarthRPG.api.npc.NpcAPI;
import ru.ItzDarth.DarthRPG.commands.BuildCommand;
import ru.ItzDarth.DarthRPG.listeners.AttackListener;
import ru.ItzDarth.DarthRPG.listeners.InteractListener;
import ru.ItzDarth.DarthRPG.listeners.InventoryListener;
import ru.ItzDarth.DarthRPG.listeners.JoinListener;
import ru.ItzDarth.DarthRPG.listeners.QuitListener;
import ru.ItzDarth.DarthRPG.listeners.ResourcePackListener;
import ru.ItzDarth.DarthRPG.timers.TimerManager;

public class DarthRPG extends JavaPlugin {
	
	public static DarthRPG INSTANCE;
	public static MySQL MYSQL;
	
	public void onEnable() {
		saveDefaultConfig();
		INSTANCE = this;
		
		// Инициализация API
		NpcAPI.onEnable(this);
		
		LanguageManager.loadSite(Language.RUSSIAN);
		LanguageManager.loadSite(Language.ENGLISH);
		
		LocationAPI.init();
		BuildAPI.init();
		TimerManager.init();
		
		MYSQL = MySQLAPI.connect("localhost", 3306, "darthrpg", "root", "");
		registerListeners(
				new JoinListener(),         // Эвент при входе игрока
				new QuitListener(),         // Эвент на выход игрока
				new InteractListener(),     // Эвент клика на моба/блок
				new ResourcePackListener(), // Эвент загрузки ресурс-пака
				new InventoryListener(),    // Эвент на клик инвентаря (API)
				new AttackListener()
				);
		
		getCommand("build").setExecutor(new BuildCommand());
	}
	
	public void onDisable() {
		NpcAPI.onDisable();
	}
	
	private void registerListeners(Listener... listeners) {
		PluginManager manager = Bukkit.getPluginManager();
		for(Listener listener : listeners) {
			manager.registerEvents(listener, INSTANCE);
		}
	}
	
}
