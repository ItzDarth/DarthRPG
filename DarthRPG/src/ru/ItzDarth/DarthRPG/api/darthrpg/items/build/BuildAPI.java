package ru.ItzDarth.DarthRPG.api.darthrpg.items.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

import ru.ItzDarth.DarthRPG.DarthRPG;
import ru.ItzDarth.DarthRPG.api.darthrpg.items.BuildItem;
import ru.ItzDarth.DarthRPG.api.itemcreator.ItemCreator;

public class BuildAPI {
	
	public static HashMap<String, BuildItem> ITEMS = new HashMap<String, BuildItem>();
	
	public static List<ArmorStand> STANDS = new ArrayList<ArmorStand>();
	
	public static void init() {
		ITEMS.put("dummy", new BuildItem(new ItemCreator(Material.STICK).setName("dummy").build()));
		ITEMS.put("chest_magic", new BuildItem(new ItemCreator(Material.COAL).setName("chest_magic").build()));
		ITEMS.put("birch_roots", new BuildItem(new ItemCreator(Material.COAL).setName("birch_roots").build()));
		ITEMS.put("oak_roots", new BuildItem(new ItemCreator(Material.COAL).setName("oak_roots").build()));
		ITEMS.put("spider egg", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("spider egg").build()));
		ITEMS.put("platinum_ore", new BuildItem(new ItemCreator(Material.IRON_ORE).setName("platinum_ore").build()));
		ITEMS.put("pointer1", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("pointer1").build()));
		ITEMS.put("pointer", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("pointer").build()));
		ITEMS.put("cauldron_kobald", new BuildItem(new ItemCreator(Material.CAULDRON).setName("cauldron_kobald").build()));
		ITEMS.put("signboard_tawern", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("signboard_tawern").build()));
		ITEMS.put("signboard_mageshop", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("signboard_mageshop").build()));
		ITEMS.put("signboard_blacksmith", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("signboard_blacksmith").build()));
		ITEMS.put("signboard_money", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("signboard_money").build()));
		ITEMS.put("signboard_potion", new BuildItem(new ItemCreator(Material.PUMPKIN).setName("signboard_potion").build()));
		
		for(String key : DarthRPG.INSTANCE.getConfig().getStringList("buildings")) {
			// world;x;y;z;yaw;pitch;dummy
			String[] dirt = key.split(";");
			World world = Bukkit.getWorld(dirt[0]);
			ArmorStand stand = world.spawn(new Location(world, Double.parseDouble(dirt[1]), Double.parseDouble(dirt[2]), Double.parseDouble(dirt[3]), Float.parseFloat(dirt[4]), Float.parseFloat(dirt[5])), ArmorStand.class);
			stand.setGravity(false);
			stand.setInvulnerable(true);
			stand.setVisible(false);
			stand.getEquipment().setHelmet(ITEMS.get(dirt[6]).getItem());
			STANDS.add(stand);
		}
	}
	
	public static void addStand(ArmorStand stand) {
		STANDS.add(stand);
		saveConfig();
	}
	
	public static void saveConfig() {
		List<String> std = new ArrayList<String>();
		List<ArmorStand> toDelete = new ArrayList<ArmorStand>();
		for(ArmorStand st : STANDS) {
			if(st == null || st.isDead()) {
				toDelete.add(st);
				continue;
			}
			std.add(st.getWorld().getName()+";"+st.getLocation().getX()+";"+st.getLocation().getY()+";"+st.getLocation().getZ()+";"+st.getLocation().getYaw()+";"+st.getLocation().getPitch()+";"+st.getEquipment().getHelmet().getItemMeta().getDisplayName());
		}
		DarthRPG.INSTANCE.getConfig().set("buildings", std);
		DarthRPG.INSTANCE.saveConfig();
		toDelete.forEach(sr -> STANDS.remove(sr));
	}
	
}
