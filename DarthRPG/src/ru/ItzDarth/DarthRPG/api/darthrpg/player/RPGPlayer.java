package ru.ItzDarth.DarthRPG.api.darthrpg.player;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.tag.TagAPI;

public class RPGPlayer {
	
	private Player PLAYER;
	public Rank RANK;
	public Language LANGUAGE;
	
	private TagAPI tagApi;
	
	public RPGPlayer(Player player) {
		this.PLAYER = player;
		
		// id, name, uuid, rank, language
		DarthRPG.MYSQL.select("SELECT * FROM darthrpg_users WHERE name=?", rs -> {
			if(rs.next() ) {
				RANK = Rank.valueOf(rs.getString("rank"));
				LANGUAGE = Language.valueOf(rs.getString("language"));
			} else {
				DarthRPG.MYSQL.insert("INSERT INTO darthrpg_users (name, uuid) VALUES (?, ?)", id -> {}, player.getName(), player.getUniqueId().toString());
				RANK = Rank.PLAYER;
				LANGUAGE = Language.RUSSIAN;
			}
		}, player.getName());
		
		updateTag();
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
				"rank=?"+
				" WHERE name=?", 
				() -> {}, 
				RANK.name(),
				
				PLAYER.getName());
	}
	
}
