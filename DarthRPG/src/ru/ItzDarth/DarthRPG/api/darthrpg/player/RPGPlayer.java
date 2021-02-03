package ru.ItzDarth.DarthRPG.api.darthrpg.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.chat.ChatAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.LocationAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem.RPGItem;
import ru.ItzDarth.DarthRPG.api.darthrpg.level.LevelManager;
import ru.ItzDarth.DarthRPG.api.language.Language;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;
import ru.ItzDarth.DarthRPG.api.tag.TagAPI;
import ru.ItzDarth.DarthRPG.menus.start.SelectLanguageGUI;
import ru.ItzDarth.DarthRPG.spells.Attack;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.attacks.MageAttack;
import ru.ItzDarth.DarthRPG.spells.spells.mage.HealSpell;
import ru.ItzDarth.DarthRPG.spells.spells.mage.MeteorSpell;
import ru.ItzDarth.DarthRPG.spells.spells.mage.TeleportSpell;
import ru.ItzDarth.DarthRPG.timers.main.MainTimer;

public class RPGPlayer {
	
	public Player PLAYER;
	public Rank RANK;
	public Language LANGUAGE;
	public zClass CLASS;
	
	public int health = 20;
	public int maxHealth = 20;
	
	public int mana = 20;
	public int maxMana = 20;
	
	public int level = 1;
	public int currentXP = 0; //***
	public int needXP = 0;
	
	public boolean LOADED = false;
	public Attack ATTACK_TYPE;
	public int CAST_SEC = 0;
	public int SHOW_SPELL = 0;
	public StringBuilder CAST_BUILDER;
	
	public Spell SPELL_RLR;
	public int SPELL_RLR_GRADLE = 1;
	
	public Spell SPELL_RRR;
	public int SPELL_RRR_GRADLE = 1;
	
	public Spell SPELL_RLL;
	public int SPELL_RLL_GRADLE = 1;
	
	private TagAPI tagApi;
	
	public RPGPlayer(Player player) {
		this.PLAYER = player;
		
		// id, name, uuid, rank, language, class, health, maxHealth, mana, level, currentXP
		DarthRPG.MYSQL.select("SELECT * FROM darthrpg_users WHERE name=?", rs -> {
			if(rs.next() ) {
				RANK = Rank.valueOf(rs.getString("rank"));
				LANGUAGE = Language.valueOf(rs.getString("language"));
				CLASS = zClass.valueOf(rs.getString("class"));
				
				health = rs.getInt("health");
				maxHealth = rs.getInt("maxHealth");
				mana = rs.getInt("mana");
				maxMana = CLASS == zClass.ARCHEMAGE ? 40 : 20;
				
				level = rs.getInt("level");
				currentXP = rs.getInt("currentXP");
				needXP = LevelManager.LEVELS[level][1];
			} else {
				DarthRPG.MYSQL.insert("INSERT INTO darthrpg_users (name, uuid) VALUES (?, ?)", id -> {}, player.getName(), player.getUniqueId().toString());
				RANK = Rank.PLAYER;
				LANGUAGE = Language.RUSSIAN;
				CLASS = zClass.NONE;
				
				health = 20;
				maxHealth = 20;
				mana = 20;
				maxMana = 20;
				
				level = 1;
				currentXP = 0;
				needXP = LevelManager.LEVELS[0][1];
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
		switch(CLASS) {
			case WARRIOR:
				break;
			case ARCHER:
				break;
			case MAGE:
				ATTACK_TYPE = new MageAttack();
				
				SPELL_RLR = new HealSpell();
				if(level >= 36) SPELL_RLR_GRADLE = 3;
				else if(level >= 16) SPELL_RLR_GRADLE = 2;
				else SPELL_RLR_GRADLE = 1;
				
				SPELL_RRR = new TeleportSpell();
				if(level >= 46) SPELL_RRR_GRADLE = 3;
				else if(level >= 26) SPELL_RRR_GRADLE = 2;
				else SPELL_RRR_GRADLE = 1;
				
				SPELL_RLL = new MeteorSpell();
				if(level >= 56) SPELL_RLL_GRADLE = 3;
				else if(level >= 36) SPELL_RLL_GRADLE = 2;
				else SPELL_RLL_GRADLE = 1;
				break;
			case BERSERK:
				break;
			case ARCHEMAGE:
				break;
			default:
				break;
		}
		LOADED = true;
	}
	
	public void checkCast(NBTTagCompound tag) {
		String result = CAST_BUILDER.toString();
		System.out.println(result);
		if(result.equals("RLR")) {
			int cost = SPELL_RLR_GRADLE == 1 ? SPELL_RLR.getCost_I() : (SPELL_RLR_GRADLE == 2 ? SPELL_RLR.getCost_II() : SPELL_RLR.getCost_III());
			if(mana < cost) {
				PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-not-mana"));
				PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1F);
				CAST_SEC = 0;
				MainTimer.updateDefaultActionBar(this);
				return;
			}
			this.mana -= cost;
			String name = LANGUAGE.getMessage(SPELL_RLR.getKeyName());
			CAST_BUILDER = new StringBuilder("§7"+name+" §b[✹ "+cost+"]");
			if(SPELL_RLR_GRADLE == 1) SPELL_RLR.runGradle_I(this, tag);
			else if(SPELL_RLR_GRADLE == 2) SPELL_RLR.runGradle_II(this, tag);
			else if(SPELL_RLR_GRADLE == 3) SPELL_RLR.runGradle_III(this, tag);
			PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-success").replace("{spell}", "§7"+name+" §b[✹ "+cost+"]"));
			MainTimer.updateSpellActionBar(this);
			CAST_SEC = 0;
			SHOW_SPELL = 2;
			return;
		} else if(result.equals("RRR")) {
			if(level >= 11) {
				int cost = SPELL_RRR_GRADLE == 1 ? SPELL_RRR.getCost_I() : (SPELL_RRR_GRADLE == 2 ? SPELL_RRR.getCost_II() : SPELL_RRR.getCost_III());
				if(mana < cost) {
					PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-not-mana"));
					PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1F);
					CAST_SEC = 0;
					MainTimer.updateDefaultActionBar(this);
					return;
				}
				this.mana -= cost;
				String name = LANGUAGE.getMessage(SPELL_RRR.getKeyName());
				CAST_BUILDER = new StringBuilder("§7"+name+" §b[✹ "+cost+"]");
				if(SPELL_RRR_GRADLE == 1) SPELL_RRR.runGradle_I(this, tag);
				else if(SPELL_RRR_GRADLE == 2) SPELL_RRR.runGradle_II(this, tag);
				else if(SPELL_RRR_GRADLE == 3) SPELL_RRR.runGradle_III(this, tag);
				PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-success").replace("{spell}", "§7"+name+" §b[✹ "+cost+"]"));
				MainTimer.updateSpellActionBar(this);
				CAST_SEC = 0;
				SHOW_SPELL = 2;
				return;
			} else {
				PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-not-combination"));
				PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1F);
			}
		}  else if(result.equals("RLL")) {
			if(level >= 21) {
				int cost = SPELL_RLL_GRADLE == 1 ? SPELL_RLL.getCost_I() : (SPELL_RLL_GRADLE == 2 ? SPELL_RLL.getCost_II() : SPELL_RLL.getCost_III());
				if(mana < cost) {
					PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-not-mana"));
					PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1F);
					CAST_SEC = 0;
					MainTimer.updateDefaultActionBar(this);
					return;
				}
				this.mana -= cost;
				String name = LANGUAGE.getMessage(SPELL_RLL.getKeyName());
				CAST_BUILDER = new StringBuilder("§7"+name+" §b[✹ "+cost+"]");
				if(SPELL_RLL_GRADLE == 1) SPELL_RLL.runGradle_I(this, tag);
				else if(SPELL_RLL_GRADLE == 2) SPELL_RLL.runGradle_II(this, tag);
				else if(SPELL_RLL_GRADLE == 3) SPELL_RLL.runGradle_III(this, tag);
				PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-success").replace("{spell}", "§7"+name+" §b[✹ "+cost+"]"));
				MainTimer.updateSpellActionBar(this);
				CAST_SEC = 0;
				SHOW_SPELL = 2;
				return;
			} else {
				PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-not-combination"));
				PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1F);
			}
		} else {
			PLAYER.sendMessage(LANGUAGE.getMessage("spell-cast-not-combination"));
			PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1F);
		}
		CAST_SEC = 0;
		MainTimer.updateDefaultActionBar(this);
	}
	
	public void levelUp() {
		level++;
		ItemStack[] items = PLAYER.getInventory().getContents();
		for(int i = 0; i < items.length; i++) {
			ItemStack item = items[i];
			NBTTagCompound tag = new ItemNBT(item).getTag();
			if(tag.hasKey("weapon")) {
				PLAYER.getInventory().setItem(i, RPGItem.updateInfoItem(this, item, tag));
			}
		}
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
				
				"level=?,"+
				"currentXP=?"+
				" WHERE name=?", 
				() -> {}, 
				RANK.name(),
				LANGUAGE.name(),
				CLASS.name(),
				
				health,
				maxHealth,
				mana,
				
				level,
				currentXP,
				
				PLAYER.getName());
	}
	
}
