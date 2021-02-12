package ru.ItzDarth.DarthRPG;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.ItemAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.build.BuildAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.mob.generator.MobType;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.language.LanguageManager;
import ru.ItzDarth.DarthRPG.api.mysql.MySQL;
import ru.ItzDarth.DarthRPG.api.mysql.MySQLAPI;
import ru.ItzDarth.DarthRPG.api.npc.NpcAPI;
import ru.ItzDarth.DarthRPG.commands.BuildCommand;
import ru.ItzDarth.DarthRPG.listeners.AttackListener;
import ru.ItzDarth.DarthRPG.listeners.EntityDeathListener;
import ru.ItzDarth.DarthRPG.listeners.HeldItemListener;
import ru.ItzDarth.DarthRPG.listeners.InteractListener;
import ru.ItzDarth.DarthRPG.listeners.InventoryListener;
import ru.ItzDarth.DarthRPG.listeners.JoinListener;
import ru.ItzDarth.DarthRPG.listeners.PlayerDamageListener;
import ru.ItzDarth.DarthRPG.listeners.QuitListener;
import ru.ItzDarth.DarthRPG.listeners.ResourcePackListener;
import ru.ItzDarth.DarthRPG.listeners.ShamanTotemListener;
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
				new AttackListener(),       // Эвент атаки посохами и кастование заклинаний
				new HeldItemListener(),     // Эвент при берении в руки предмета
				new EntityDeathListener(),  // Эвент при убийстве мобов
				new PlayerDamageListener(), // Эвент при получении дамага игрока
				new ShamanTotemListener()   // Эвент при создании тотема шамана
				);
		
		getCommand("build").setExecutor(new BuildCommand());
		getCommand("test").setExecutor(new CommandExecutor() {
			public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
				Player p = (Player) sender;
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("item")) {
						RPGPlayer rp = RPGApi.getRPGPlayer(p);
						if(rp.CLASS == zClass.MAGE) {
							p.getInventory().addItem(ItemAPI.WOODEN_STAFF.generateItem(RPGApi.getRPGPlayer(p), 1));
						}
						else if(rp.CLASS == zClass.WARRIOR) {
							p.getInventory().addItem(ItemAPI.WOODEN_SWORD.generateItem(RPGApi.getRPGPlayer(p), 1));
						}
						else if(rp.CLASS == zClass.ARCHER) {
							p.getInventory().addItem(ItemAPI.WOODEN_BOW.generateItem(RPGApi.getRPGPlayer(p), 1));
						}
						else if(rp.CLASS == zClass.SHAMAN) {
							p.getInventory().addItem(ItemAPI.WOODEN_RELIC.generateItem(RPGApi.getRPGPlayer(p), 1));
						}
					}
					else if(args[0].equalsIgnoreCase("mob")) {
						MobType.ZOMBIE.spawnMob(p.getLocation(), 1, 0);
					}
				}
				return true;
			}
		});
	}
	
	public void onDisable() {
		NpcAPI.onDisable();
		
		for(World world : Bukkit.getWorlds()) {
			for(Entity entity : world.getEntities()) {
				entity.remove();
			}
		}
	}
	
	private void registerListeners(Listener... listeners) {
		PluginManager manager = Bukkit.getPluginManager();
		for(Listener listener : listeners) {
			manager.registerEvents(listener, INSTANCE);
		}
	}
	
}
