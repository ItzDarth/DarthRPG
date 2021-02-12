package ru.ItzDarth.DarthRPG.timers.main;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.chat.ChatAPI;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.utils.Utils;

public class MainTimer {
	
	public MainTimer() {
		new BukkitRunnable() {
			public void run() {
				for(RPGPlayer rp : RPGApi.RPGPLAYERS.values()) {
					if(rp.LOADED) {
						if(rp.SHOW_SPELL > 0) {
							updateSpellActionBar(rp);
							rp.SHOW_SPELL--;
						}
						else if(rp.CAST_SEC > 0) {
							updateSpellActionBar(rp);
							rp.CAST_SEC--;
							if(rp.CAST_SEC == 0) {
								rp.CAST_BUILDER = new StringBuilder();
							}
						} else {
							updateDefaultActionBar(rp);
						}
						
						if(rp.noPvpTime == 0) {
							if(rp.health < rp.maxHealth) {
								int sum = rp.health+5+rp.temp_Health_Regen;
								rp.health = sum > rp.maxHealth ? rp.maxHealth : sum;
							}
						}
						if(rp.mana < rp.maxMana) {
							int sum = rp.mana+1+rp.temp_Mana_Regen;
							rp.mana = sum > rp.maxMana ? rp.maxMana : sum;
						}
						if(rp.noPvpTime > 0) {
							rp.noPvpTime--;
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(DarthRPG.INSTANCE, 0L, 20L);
	}
	
	public static void updateSpellActionBar(RPGPlayer rp) {
		ChatAPI.sendActionBar(rp.PLAYER, 
				"§8[§c❤ "+rp.health+"/"+rp.maxHealth+"§8]"+
				"    §7"+
				formatSpell(rp)+
				"    §8[§b✹ "+rp.mana+"/"+rp.maxMana+"§8]"
				);
	}
	
	public static void updateDefaultActionBar(RPGPlayer rp) {
		Location loc = rp.PLAYER.getLocation();
		ChatAPI.sendActionBar(rp.PLAYER, 
				"§8[§c❤ "+rp.health+"/"+rp.maxHealth+"§8]"+
				"    §7"+
				loc.getBlockX()+" §f"+Utils.getCardinalDirection(rp.PLAYER)+" §7"+loc.getBlockZ()+
				"    §8[§b✹ "+rp.mana+"/"+rp.maxMana+"§8]"
				);
	}
	
	private static String formatSpell(RPGPlayer rp) {
		StringBuilder build = rp.CAST_BUILDER;
		if(build.length() == 1) {
			return "§a"+build.charAt(0)+"§7-§n?§7-§n?§r";
		}
		else if(build.length() == 2) {
			return "§a"+build.charAt(0)+"§7-§a"+build.charAt(1)+"§7-§n?§r";
		}
		else if(build.length() == 3) {
			return "§a"+build.charAt(0)+"§7-§a"+build.charAt(1)+"§7-§a"+build.charAt(2);
		}
		return build.toString();
	}
	
}
