package ru.ItzDarth.DarthRPG.api.hologram;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import ru.ItzDarth.DarthRPG.DarthRPG;

public class Hologram {
	
    private List<EntityArmorStand> entitylist = new ArrayList<EntityArmorStand>();
    private String[] Text;
    private List<String> textA;
    private Location location;
    private double DISTANCE = 0.25D;
    int count;
 
    public Hologram(String[] Text, Location location) {
        this.Text = Text;
        this.location = location;
        create(true);
    }
    
    public Hologram(List<String> text, Location location) {
        this.textA = text;
        this.location = location;
        create(false);
    }
    
    public void showAllPlayerAtTemp(int seconds) {
    	for(Player p : Bukkit.getOnlinePlayers()) {
    		for (EntityArmorStand armor : entitylist) {
                PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
            }
    	}
    	new BukkitRunnable() {
    		public void run() {
    			for(Player p : Bukkit.getOnlinePlayers()) {
    				for (EntityArmorStand armor : entitylist) {
    		            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
    		            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    		        }
    	    	}
    		}
    	}.runTaskLater(DarthRPG.INSTANCE, seconds*20);
    }
    
    public void showPlayer(Player p) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }
 
    public void hidePlayer(Player p) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }
    
    private void create(boolean fdg) {
    	if(fdg) {
	        for (String Text : this.Text) {
	            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) this.location.getWorld()).getHandle(),this.location.getX(), this.location.getY(),this.location.getZ());
	            entity.setCustomName(Text);
	            entity.setCustomNameVisible(true);
	            entity.setInvisible(true);
	            entity.setNoGravity(true);
	            entitylist.add(entity);
	            this.location.subtract(0, this.DISTANCE, 0);
	            count++;
	        }
    	} else {
    		for (String Text : this.textA) {
	            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) this.location.getWorld()).getHandle(),this.location.getX(), this.location.getY(),this.location.getZ());
	            entity.setCustomName(Text);
	            entity.setCustomNameVisible(true);
	            entity.setInvisible(true);
	            entity.setNoGravity(true);
	            entitylist.add(entity);
	            this.location.subtract(0, this.DISTANCE, 0);
	            count++;
	        }
    	}
 
        for (int i = 0; i < count; i++) {
            this.location.add(0, this.DISTANCE, 0);
        }
        this.count = 0;
    }
	
}

