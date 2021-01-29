package ru.ItzDarth.DarthRPG.api.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.ItzDarth.DarthRPG.api.npc.nms.PacketInjector;
import ru.ItzDarth.DarthRPG.api.npc.nms.ReflectionUtil;

public class NpcAPI implements Listener {

    private static HashMap<Player, List<NPC>> npcs;
    private static PacketInjector packetInjector;

    public static Collection<List<NPC>> getNPCS() {
        return npcs.values();
    }

    public static PacketInjector getPacketInjector() {
        return packetInjector;
    }

    public static NPC createNPC(String name, String value, String signature, Location location, Boolean name_visible, Player receiver) {
        NPC npc = ReflectionUtil.newNPC(name, value, signature, location, receiver, name_visible);
        if(npcs.containsKey(receiver)) {
        	List<NPC> cNpc = npcs.get(receiver);
        	cNpc.add(npc);
        	npcs.replace(receiver, cNpc);
        } else {
        	List<NPC> cNpc = new ArrayList<NPC>();
        	cNpc.add(npc);
        	npcs.put(receiver, cNpc);
        }
        npc.spawn();
        return npc;
    }

    public static void removeNPC(Player p, NPC npc) {
        npcs.remove(p, npc);
        npc.despawn();
    }
    
    public static JavaPlugin plugin;

    public static void onEnable(JavaPlugin plugin) {
    	NpcAPI.plugin = plugin;
        npcs = new HashMap<Player, List<NPC>>();
        ReflectionUtil.init();
        packetInjector = new PacketInjector();
        plugin.getServer().getPluginManager().registerEvents(new NpcAPI(), plugin);
    }
    
    @EventHandler
    public void onPlayerQuiter(PlayerQuitEvent e) {
    	Player p = e.getPlayer();
    	npcs.remove(p);
    }
    
    public static void onDisable() {
        if (!getNPCS().isEmpty()) {
            for (List<NPC> npcd : getNPCS()) {
                for(NPC npc : npcd) {
                	if (npc.isMoving()) {
                        npc.getWitch().remove();
                    }
                }
            }
        }
    }
}