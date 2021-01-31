package ru.ItzDarth.DarthRPG.api.darthrpg.player;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.chat.ChatAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.tag.TagAPI;
import ru.ItzDarth.DarthRPG.menus.start.SelectLanguageGUI;

public class RPGPlayer {
	
	private Player PLAYER;
	public Rank RANK;
	public Language LANGUAGE;
	public zClass CLASS;
	
	private TagAPI tagApi;
	
	public RPGPlayer(Player player) {
		this.PLAYER = player;
		
		// id, name, uuid, rank, language, | class
		// Hello Darth!
		DarthRPG.MYSQL.select("SELECT * FROM darthrpg_users WHERE name=?", rs -> {
			if(rs.next() ) {
				RANK = Rank.valueOf(rs.getString("rank"));
				LANGUAGE = Language.valueOf(rs.getString("language"));
				CLASS = zClass.valueOf(rs.getString("class"));
			} else {
				DarthRPG.MYSQL.insert("INSERT INTO darthrpg_users (name, uuid) VALUES (?, ?)", id -> {}, player.getName(), player.getUniqueId().toString());
				RANK = Rank.PLAYER;
				LANGUAGE = Language.RUSSIAN;
				CLASS = zClass.NONE;
			}
		}, player.getName());
		
		updateTag();
		
		player.teleport(LocationAPI.LOADING_LOCATION);
		ChatAPI.sendTitle(player, LANGUAGE.getMessage("loading-game-title"), LANGUAGE.getMessage("loading-game-subtitle"), 5, 40, 5);
		player.setResourcePack("http://aimatt.hldns.ru/rpg/rp/DarthRPG.zip");
	}
	
	public void checkIfClass() {
		if(CLASS == zClass.NONE) {
			new SelectLanguageGUI(PLAYER);
		} else {
			loadAndStart(false);
		}
	}
	
	public void loadAndStart(boolean tutorial) {
		
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
				"language=?"+
				" WHERE name=?", 
				() -> {}, 
				RANK.name(),
				LANGUAGE.name(),
				
				PLAYER.getName());
	}
	
}
