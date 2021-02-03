package ru.ItzDarth.DarthRPG.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
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
import ru.ItzDarth.DarthRPG.timers.main.MainTimer;

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
				if(rp.CAST_SEC == 0) {
					if(p.hasCooldown(item.getType())) return;
					
					zClass weaponClass = zClass.valueOf(tag.getString("class"));
					if(weaponClass != zClass.MAGE && weaponClass != zClass.ARCHEMAGE) return;
					if(rp.CLASS != weaponClass) {
						p.sendMessage(rp.LANGUAGE.getMessage("item-attack-class"));
						return;
					}
					
					int weaponLvl = tag.getInt("level");
					if(rp.level < weaponLvl) {
						p.sendMessage(rp.LANGUAGE.getMessage("item-attack-lvl"));
						return;
					}
					
					rp.ATTACK_TYPE.run(rp, item, tag);
				} else { // Кастуем на L
					rp.CAST_BUILDER.append("L");
					if(checkSize(rp, tag)) {
						rp.CAST_SEC = 3;
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						MainTimer.updateSpellActionBar(rp);
					}
				}
			}
		} else if(action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) { // Кастуем на R
			ItemNBT nbt = new ItemNBT(item);
			NBTTagCompound tag = nbt.getTag();
			if(tag.hasKey("weapon")) {
				RPGPlayer rp = RPGApi.getRPGPlayer(p);
				if(rp.CAST_SEC == 0) {
					rp.CAST_BUILDER = new StringBuilder("R");
					rp.CAST_SEC = 3;
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					MainTimer.updateSpellActionBar(rp);
				} else {
					rp.CAST_BUILDER.append("R");
					if(checkSize(rp, tag)) {
						rp.CAST_SEC = 3;
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						MainTimer.updateSpellActionBar(rp);
					}
				}
			}
		}
	}
	
	private boolean checkSize(RPGPlayer rp, NBTTagCompound tag) {
		if(rp.CAST_BUILDER.length() == 3) {
			// проверяем каст на существование
			rp.checkCast(tag);
			return false;
		}
		return true;
	}
	
}
