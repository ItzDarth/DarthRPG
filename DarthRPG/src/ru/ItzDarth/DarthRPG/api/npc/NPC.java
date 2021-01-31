package ru.ItzDarth.DarthRPG.api.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import ru.ItzDarth.DarthRPG.api.npc.enums.Action;
import ru.ItzDarth.DarthRPG.api.npc.enums.Animation;
import ru.ItzDarth.DarthRPG.api.npc.enums.EquipmentAction;
import ru.ItzDarth.DarthRPG.api.npc.enums.Status;
import ru.ItzDarth.DarthRPG.api.npc.nms.ReflectionUtil;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public abstract class NPC {

    private String name;
    private String value, signature;
    private Location location;
    private GameProfile gameProfile;
    private Player receiver;
    private Action action;
    private Boolean name_visible;
    private Boolean moving;

    public NPC(String name, String value, String signature, Location location, Player receiver, Action action, Boolean name_visible) {
        this.name = name;
        this.value = value;
        this.signature = signature;
        this.location = location;
        this.receiver = receiver;
        this.action = action;
        this.name_visible = name_visible;
        this.moving = false;
    }

    public Boolean getNameVisible() {
        return name_visible;
    }

    public Boolean isMoving() {
        return moving;
    }

    public void cancelMovement() {
        if (isMoving()) {
            moving = false;
            getWitch().remove();
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }

    public Location getLocation() {
        return location;
    }

    public Player getReceiver() {
        return receiver;
    }

    public Action getAction() {
        return action;
    }

	public void spawn() {
        gameProfile = new GameProfile(UUID.randomUUID(), getName());
        gameProfile.getProperties().put("textures", new Property("textures", getValue(), getSignature()));
        setupEntity();
        ReflectionUtil.sendPacket(receiver, getPlayerInfoPacket());
        ReflectionUtil.sendPacket(receiver, getSpawnPacket());
        ReflectionUtil.sendPacket(receiver, getHeadRotationPacket());
        ReflectionUtil.sendPacket(receiver, getEntityMetaPacket());
        if (!name_visible) {
            ReflectionUtil.sendPacket(receiver, getHideNamePacket());
            ReflectionUtil.sendPacket(receiver, this.getHideTabNamePacket());
        }
        NpcAPI.getPacketInjector().addPlayer(receiver);
    }

    public void teleport(Location location) {
        setLocation(location);
        ReflectionUtil.sendPacket(receiver, getTeleportPacket(location));
        ReflectionUtil.sendPacket(receiver, getHeadRotationPacket());
    }

    public void setTarget(Location target, Float speed, Double distance_stop) {
        if (getLocation().distance(target) > 15.95) {
            System.out.println("[NPC-API] The distance cannot be longer than 15.95 blocks!");
            return;
        }
        if (isMoving()) {
            cancelMovement();
        }
        targetWitch(target, speed);
        moving = true;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isMoving()) {
                    if (getWitch().getLocation().distance(target) <= distance_stop) {
                        cancelMovement();
                        this.cancel();
                    } else {
                        setLocation(getWitch().getLocation());
                        ReflectionUtil.sendPacket(receiver, getHeadRotationPacket());
                        teleport(getWitch().getLocation());
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(NpcAPI.plugin, 0, 1L);
    }

    public void setTarget(Entity entity, Float speed, Double distance_stop) {
        if (getLocation().distance(entity.getLocation()) > 15.95) {
            System.out.println("[NPC-API] The distance cannot be longer than 15.95 blocks!");
            return;
        }
        if (isMoving()) {
            cancelMovement();
        }
        moving = true;
        targetWitch(entity.getLocation(), speed);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isMoving()) {
                    if (!entity.isValid()) {
                        cancel();
                        this.cancel();
                        return;
                    }
                    if (getLocation().distance(entity.getLocation()) > 15.95) {
                        System.out.println("[NPC-API] The distance cannot be longer than 15.95 blocks!");
                        cancelMovement();
                        this.cancel();
                        return;
                    }
                    if (getWitch().getLocation().distance(entity.getLocation()) <= distance_stop) {
                        Vector dirBetweenLocations = entity.getLocation().toVector().subtract(getLocation().toVector());
                        Location target = getWitch().getLocation().clone();
                        target.setDirection(dirBetweenLocations);
                        setLocation(target);
                        teleport(target);
                        removePathFinders(getWitch());
                    } else {
                        Vector dirBetweenLocations = entity.getLocation().toVector().subtract(getLocation().toVector());
                        Location target = getWitch().getLocation().clone();
                        target.setDirection(dirBetweenLocations);
                        setLocation(target);
                        teleport(target);
                        changeTarget(getWitch(), entity.getLocation(), speed);
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(NpcAPI.plugin, 0, 1L);
    }

    public void playAnimation(Animation animation) {
        ReflectionUtil.sendPacket(receiver, getAnimationPacket(animation));
    }

    public void playStatus(Status status) {
        ReflectionUtil.sendPacket(receiver, getStatusPacket(status));
    }

    public void despawn() {
        if (getAction() == Action.ADD_PLAYER) {
            setAction(Action.REMOVE_PLAYER);
        }
        ReflectionUtil.sendPacket(receiver, getDestroyPacket());
        NpcAPI.getPacketInjector().removePlayer(receiver);
    }

    public void setAction(Action action) {
        this.action = action;
        ReflectionUtil.sendPacket(receiver, getPlayerInfoPacket());
    }

    public void setEquipment(EquipmentAction action , ItemStack itemStack) {
        ReflectionUtil.sendPacket(receiver, getEquipPacket(itemStack, action));
    }

    private void setLocation(Location location) {
        this.location = location;
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }


    
    public abstract void setupEntity();

    
    public abstract void targetWitch(Location location, Float speed);

    
    public abstract void changeTarget(Entity entity, Location location, Float speed);

    
    public abstract void removePathFinders(Entity entity);

    
    public abstract Witch getWitch();

    
    public abstract Object getPlayerInfoPacket();

    
    public abstract Object getSpawnPacket();

    
    public abstract Object getDestroyPacket();

    
    public abstract Object getHeadRotationPacket();

    
    public abstract Object getTeleportPacket(Location loc);

    
    public abstract Object getAnimationPacket(Animation animation);

    
    public abstract Object getStatusPacket(Status status);

    
    public abstract Object getHideNamePacket();
    
    public abstract PacketPlayOutPlayerInfo getHideTabNamePacket();
    
    public abstract Object getEntityMetaPacket();

    
    public abstract Object getEquipPacket(ItemStack itemStack, EquipmentAction action);

    
    public abstract Integer getID();
}