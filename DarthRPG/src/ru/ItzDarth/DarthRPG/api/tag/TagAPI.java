package ru.ItzDarth.DarthRPG.api.tag;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.Packet;
import ru.ItzDarth.DarthRPG.api.reflection.ReflectionAPI;

public class TagAPI {
	
	Player player;
	String prefix;
	int priority;
	String team_name = "";
	
    public TagAPI(Player player, String prefix, int priority) {
    	this.player = player;
    	this.prefix = prefix;
    	this.priority = priority;
    	team_name = ((char)(priority+64))+player.getName();
        if(team_name.length() > 16) {
            team_name = team_name.substring(0, 16);
        }
        Packet<?> packet_add = createPacketAdd(team_name);
        Packet<?> packet_delete = createPacketDelete(team_name);
        
        for(Player p : Bukkit.getOnlinePlayers()) {
        	ReflectionAPI.sendPacket(p, packet_delete);
        	ReflectionAPI.sendPacket(p, packet_add);
        }
    }
    
    public void setPrefix(String prefix) {
    	this.prefix = prefix;
    }
    
    public void setPriority(int priority) {
    	this.priority = priority;
    }
    
    public void updateForPlayer(Player p) {
    	team_name = ((char)(priority+64))+player.getName();
        if(team_name.length() > 16) {
            team_name = team_name.substring(0, 16);
        }
        Packet<?> packet_add = createPacketAdd(team_name);
        Packet<?> packet_delete = createPacketDelete(team_name);
        
        ReflectionAPI.sendPacket(p, packet_delete);
        ReflectionAPI.sendPacket(p, packet_add);
    }
    
    public void update() {
    	team_name = ((char)(priority+64))+player.getName();
        if(team_name.length() > 16) {
            team_name = team_name.substring(0, 16);
        }
        Packet<?> packet_add = createPacketAdd(team_name);
        Packet<?> packet_delete = createPacketDelete(team_name);
        
        for(Player p : Bukkit.getOnlinePlayers()) {
        	ReflectionAPI.sendPacket(p, packet_delete);
        	ReflectionAPI.sendPacket(p, packet_add);
        }
    }
    
    public void remove() {
    	Packet<?> packet_delete = createPacketDelete(team_name);
        
        for(Player p : Bukkit.getOnlinePlayers()) {
        	ReflectionAPI.sendPacket(p, packet_delete);
        }
    }
	
    private Packet<?> createPacketAdd(String team_name) {
    	try {
            Constructor<?> constructor = ReflectionAPI.getNMSClass("PacketPlayOutScoreboardTeam").getConstructor();
            Packet<?> packet = (Packet<?>) constructor.newInstance();
            List<String> contents = new ArrayList<>();
            contents.add(player.getName());
            	
            ReflectionAPI.setField(packet, "a", team_name);
            ReflectionAPI.setField(packet, "b", team_name);
            ReflectionAPI.setField(packet, "c", prefix);
            ReflectionAPI.setField(packet, "e", "ALWAYS");
            ReflectionAPI.setField(packet, "h", contents);
            ReflectionAPI.setField(packet, "i", 0);
            //ReflectionAPI.setField(packet, "h", 0);
            //ReflectionAPI.setField(packet, "g", contents);
            
            return packet;
        } catch(Exception e) {}
    	
		return null;
    }
    
    private Packet<?> createPacketDelete(String team_name) {
    	try {
	    	Constructor<?> constructor = ReflectionAPI.getNMSClass("PacketPlayOutScoreboardTeam").getConstructor();
	    	Packet<?> packet = (Packet<?>) constructor.newInstance();
	        
        	ReflectionAPI.setField(packet, "a", team_name);
            ReflectionAPI.setField(packet, "b", team_name);
            ReflectionAPI.setField(packet, "c", prefix);
            ReflectionAPI.setField(packet, "e", "ALWAYS");
            ReflectionAPI.setField(packet, "i", 1);
            //ReflectionAPI.setField(packet, "h", 1);
            
            return packet;
        } catch(Exception ex) {ex.printStackTrace();}
    	
    	return null;
    }
    
}
