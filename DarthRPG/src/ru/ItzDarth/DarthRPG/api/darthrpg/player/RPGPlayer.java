package ru.ItzDarth.DarthRPG.api.darthrpg.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.scheduler.BukkitRunnable;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.chat.ChatAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.tag.TagAPI;
import ru.ItzDarth.DarthRPG.menus.start.SelectLanguageGUI;

public class RPGPlayer {
	
	public Player PLAYER;
	public Rank RANK;
	public Language LANGUAGE;
	public zClass CLASS;
	
	//
	
	public int health = 20;
	public int maxHealth = 20;
	
	public int mana = 20;
	public int maxMana = 20;
	
	public int level = 1;//
	
	public boolean LOADED = false;
	
	private TagAPI tagApi;
	
	public RPGPlayer(Player player) {
		this.PLAYER = player;
		
		// id, name, uuid, rank, language, class, health, maxHealth, mana
		DarthRPG.MYSQL.select("SELECT * FROM darthrpg_users WHERE name=?", rs -> {
			if(rs.next() ) {
				RANK = Rank.valueOf(rs.getString("rank"));
				LANGUAGE = Language.valueOf(rs.getString("language"));
				CLASS = zClass.valueOf(rs.getString("class"));
				health = rs.getInt("health");
				maxHealth = rs.getInt("maxHealth");
				mana = rs.getInt("mana");
				maxMana = CLASS == zClass.ARCHEMAGE ? 40 : 20;
			} else {
				DarthRPG.MYSQL.insert("INSERT INTO darthrpg_users (name, uuid) VALUES (?, ?)", id -> {}, player.getName(), player.getUniqueId().toString());
				RANK = Rank.PLAYER;
				LANGUAGE = Language.RUSSIAN;
				CLASS = zClass.NONE;
				health = 20;
				maxHealth = 20;
				mana = 20;
				maxMana = 20;
			}
		}, player.getName());
		
		updateTag();
		for(RPGPlayer rpg : RPGApi.RPGPLAYERS.values()) {
			rpg.updateTag();
		}
		
		player.teleport(LocationAPI.LOADING_LOCATION);
		ChatAPI.sendTitle(player, LANGUAGE.getMessage("loading-game-title"), LANGUAGE.getMessage("loading-game-subtitle"), 5, 60, 5);
		//player.setResourcePack("http://aimatt.hldns.ru/rpg/rp/DarthRPG.zip");
		// временный фикс чтобы не ждать
		new BukkitRunnable() {
			public void run() {
				Bukkit.getPluginManager().callEvent(new PlayerResourcePackStatusEvent(player, Status.SUCCESSFULLY_LOADED));
			}
		}.runTaskLater(DarthRPG.INSTANCE, 20L);
	}
	
	public void checkIfClass() {
		if(CLASS == zClass.NONE) {
			new SelectLanguageGUI(PLAYER);
		} else {
			loadAndStart(false);
		}
	}
	
	public void loadAndStart(boolean tutorial) {
		maxMana = CLASS == zClass.ARCHEMAGE ? 40 : 20;
		LOADED = true;
	}
	
	public void updateTag() {
		if(tagApi == null) {
			tagApi = new TagAPI(PLAYER, RANK.getPrefix(), RANK.getPriority());
			return;
		}
		tagApi.update();
	}
	
	public void remove() {
		DarthRPG.MYSQL.async().update("UPDATE darthrpg_users SET "+
				"rank=?,"+
				"language=?,"+
				"class=?,"+
				
				"health=?,"+
				"maxHealth=?,"+
				"mana=?,"+
				" WHERE name=?", 
				() -> {}, 
				RANK.name(),
				LANGUAGE.name(),
				CLASS.name(),
				health,
				maxHealth,
				mana,
				
				PLAYER.getName());
	}
	
}
