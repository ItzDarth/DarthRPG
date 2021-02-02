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
						Location loc = rp.PLAYER.getLocation();
						ChatAPI.sendActionBar(rp.PLAYER, "§c❤ "+rp.health+"/"+rp.maxHealth+"    §7"+loc.getBlockX()+" §f"+Utils.getCardinalDirection(rp.PLAYER)+" §7"+loc.getBlockZ()+"    §b✹ "+rp.mana+"/"+rp.maxMana);
					}
				}
			}
		}.runTaskTimerAsynchronously(DarthRPG.INSTANCE, 0L, 20L);
	}
	
}
