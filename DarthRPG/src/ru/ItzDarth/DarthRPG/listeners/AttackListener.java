package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.zClass;
import ru.ItzDarth.DarthRPG.api.nbt.ItemNBT;

public class AttackListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(item == null || item.getType() == Material.AIR) return;
		
		if(action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) { // Обычная атака
			ItemNBT nbt = new ItemNBT(item);
			NBTTagCompound tag = nbt.getTag();
			if(tag.hasKey("weapon")) {
				RPGPlayer rp = RPGApi.getRPGPlayer(p);
				
				zClass weaponClass = zClass.valueOf(tag.getString("class"));
				if(rp.CLASS != weaponClass) {
					p.sendMessage(rp.LANGUAGE.getMessage("item-attack-class"));
					return;
				}
				
				int weaponLvl = tag.getInt("level");
				if(rp.level < weaponLvl) {
					p.sendMessage(rp.LANGUAGE.getMessage("item-attack-lvl"));
					return;
				}
				
				//item.c
			}
		}
	}
	
}
