package ru.ItzDarth.DarthRPG.api.npc.nms;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.util.UnsafeList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EntityWitch;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import net.minecraft.server.v1_12_R1.MathHelper;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;
import ru.ItzDarth.DarthRPG.api.npc.NPC;
import ru.ItzDarth.DarthRPG.api.npc.enums.Action;
import ru.ItzDarth.DarthRPG.api.npc.enums.Animation;
import ru.ItzDarth.DarthRPG.api.npc.enums.EquipmentAction;
import ru.ItzDarth.DarthRPG.api.npc.enums.Status;

public class v1_8 extends NPC {

    private Object entity_player;
    private Witch entity_witch;
    private Integer id;

    public v1_8(String name, String value, String signature, Location location, Player receiver, Action action, Boolean name_visible) {
        super(name, value, signature, location, receiver, action, name_visible);
    }

    public void setupEntity() {
        Class<?> EntityPlayer = ReflectionUtil.getCraftClass("EntityPlayer");
        Object MinecraftServer = null;
        Object WorldServer = null;
        Object InteractManager = null;
        try {
            MinecraftServer = ReflectionUtil.getMethod(ReflectionUtil.getBukkitClass("CraftServer"), "getServer").invoke(Bukkit.getServer());
            WorldServer = ReflectionUtil.getBukkitClass("CraftWorld").getMethod("getHandle").invoke(getLocation().getWorld());
            InteractManager = ReflectionUtil.getCraftClass("PlayerInteractManager").getDeclaredConstructors()[0].newInstance(WorldServer);
            entity_player = EntityPlayer.getConstructors()[0].newInstance(MinecraftServer, WorldServer, getGameProfile(), InteractManager);
            entity_player.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class).invoke(entity_player,
                    getLocation().getX(),
                    getLocation().getY(),
                    getLocation().getZ(),
                    getLocation().getYaw(),
                    getLocation().getPitch());
            Method getId = ReflectionUtil.getMethod(EntityPlayer, "getId", new Class<?>[] {});
            id = (Integer) getId.invoke(entity_player);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    
    public PacketPlayOutPlayerInfo getHideTabNamePacket() {
    	PacketPlayOutPlayerInfo playOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, (EntityPlayer) entity_player);
    	return playOutPlayerInfo;
    }
    
    public void targetWitch(Location location, Float speed) {
        EntityWitch witch = new EntityWitch(((CraftWorld) location.getWorld()).getHandle());
        overrideBehavior(witch, location, speed);
        witch.setLocation(getLocation().getX(), getLocation().getY(), getLocation().getZ(), getLocation().getYaw(), getLocation().getPitch());
        witch.setInvisible(true);
        ((CraftWorld) location.getWorld()).getHandle().addEntity(witch);
        entity_witch = (Witch) witch.getBukkitEntity();
        entity_witch.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000000, 1, false, false));
        entity_witch.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000000, 3, false, false));
        entity_witch.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000000, 3, false, false));
        entity_witch.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000000, 100, false, false));
        entity_witch.setNoDamageTicks(1000000000);
    }

    public Witch getWitch() {
        return entity_witch;
    }

    public void removePathFinders(Entity entity) {
        EntityCreature c = (EntityCreature) ((CraftEntity) entity).getHandle();
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            bField.set(c.goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(c.targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(c.goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(c.targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void overrideBehavior(EntityCreature c, Location location, Float speed) {
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            bField.set(c.goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(c.targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(c.goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(c.targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        c.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
        c.targetSelector.a(3, new v1_8Path(c, speed, location.getX(), location.getY(), location.getZ()));
    }

    public void changeTarget(Entity entity, Location location, Float speed) {
        EntityCreature c = (EntityCreature) ((CraftEntity) entity).getHandle();
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            bField.set(c.goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(c.targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(c.goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(c.targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        c.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
        c.targetSelector.a(3, new v1_8Path(c, speed, location.getX(), location.getY(), location.getZ()));
    }

    public Object getPlayerInfoPacket() {
        Object packet = null;
        try {
            Object PlayerEnum = ReflectionUtil.getCraftClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction").getField(getAction().name()).get(null);
            Constructor<?> PacketPlayOutPlayerInfoConstructor = ReflectionUtil.getCraftClass("PacketPlayOutPlayerInfo").getConstructor(ReflectionUtil.getCraftClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction"), Class.forName("[Lnet.minecraft.server." + ReflectionUtil.version + "EntityPlayer;"));
            Object array = Array.newInstance(ReflectionUtil.getCraftClass("EntityPlayer"), 1);
            Array.set(array, 0, entity_player);
            packet = PacketPlayOutPlayerInfoConstructor.newInstance(PlayerEnum, array);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getSpawnPacket() {
        Object packet = null;
        try {
            Constructor<?> PacketPlayOutNamedEntitySpawnConstructor = ReflectionUtil.getCraftClass("PacketPlayOutNamedEntitySpawn").getConstructor(ReflectionUtil.getCraftClass("EntityHuman"));
            packet = PacketPlayOutNamedEntitySpawnConstructor.newInstance(entity_player);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getHeadRotationPacket() {
        Object packet = null;
        try {
            Constructor<?> PacketPlayOutEntityHeadRotationConstructor = ReflectionUtil.getCraftClass("PacketPlayOutEntityHeadRotation").getConstructor(ReflectionUtil.getCraftClass("Entity"), byte.class);
            float yaw = getLocation().getYaw();
            packet = PacketPlayOutEntityHeadRotationConstructor.newInstance(entity_player, (byte) (yaw * 256 / 360));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getDestroyPacket() {
        Object packet = null;
        try {
            Constructor<?> PacketPlayOutEntityDestroyConstructor = ReflectionUtil.getCraftClass("PacketPlayOutEntityDestroy").getConstructor(int[].class);
            packet = PacketPlayOutEntityDestroyConstructor.newInstance(new int[] {getID()});
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getTeleportPacket(Location loc) {
        Class<?> PacketPlayOutEntityTeleport = ReflectionUtil.getCraftClass("PacketPlayOutEntityTeleport");
        Object packet = null;
        try {
            packet = PacketPlayOutEntityTeleport.getConstructor(new Class<?>[] {int.class, int.class, int.class, int.class, byte.class, byte.class, boolean.class}).newInstance(id, getFixLocation(loc.getX()), getFixLocation(loc.getY()), getFixLocation(loc.getZ()), (byte) ((int) loc.getYaw() * 256 / 360), (byte) ((int) loc.getPitch() * 256 / 360), false);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getAnimationPacket(Animation animation) {
        Class<?> Entity = ReflectionUtil.getCraftClass("Entity");
        Class<?> PacketPlayOutAnimation = ReflectionUtil.getCraftClass("PacketPlayOutAnimation");
        Object packet = null;
        try {
            packet = PacketPlayOutAnimation.getConstructor(new Class<?>[] {Entity, int.class}).newInstance(entity_player, animation.getValue());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getStatusPacket(Status status) {
        Class<?> Entity = ReflectionUtil.getCraftClass("Entity");
        Class<?> PacketPlayOutAnimation = ReflectionUtil.getCraftClass("PacketPlayOutEntityStatus");
        Object packet = null;
        try {
            packet = PacketPlayOutAnimation.getConstructor(new Class<?>[] {Entity, byte.class}).newInstance(entity_player, status.getValue());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return packet;
    }

    @SuppressWarnings("unchecked")
	public Object getHideNamePacket() {
        Class<?> PacketPlayOutScoreboardTeam = ReflectionUtil.getCraftClass("PacketPlayOutScoreboardTeam");
        Object packet = null;
        try {
            packet = PacketPlayOutScoreboardTeam.newInstance();
            Field a = ReflectionUtil.getField(packet.getClass(), "a");
            a.setAccessible(true);
            Field b = ReflectionUtil.getField(packet.getClass(), "b");
            b.setAccessible(true);
            Field c = ReflectionUtil.getField(packet.getClass(), "c");
            c.setAccessible(true);
            Field d = ReflectionUtil.getField(packet.getClass(), "d");
            d.setAccessible(true);
            Field e = ReflectionUtil.getField(packet.getClass(), "e");
            e.setAccessible(true);
            Field f = ReflectionUtil.getField(packet.getClass(), "f");
            f.setAccessible(true);
            Field g = ReflectionUtil.getField(packet.getClass(), "g");
            g.setAccessible(true);
            Field h = ReflectionUtil.getField(packet.getClass(), "h");
            h.setAccessible(true);
            Field i = ReflectionUtil.getField(packet.getClass(), "i");
            i.setAccessible(true);
            a.set(packet, getGameProfile().getName());
            b.set(packet, getGameProfile().getName());
            e.set(packet, "never");
            h.set(packet, 0);
            i.set(packet, 1);
            ((Collection<String>) g.get(packet)).add(getGameProfile().getName());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getEquipPacket(ItemStack itemStack, EquipmentAction action) {
       Class<?> PacketPlayOutEntityEquipment = ReflectionUtil.getCraftClass("PacketPlayOutEntityEquipment");
       Class<?> ItemStack = ReflectionUtil.getCraftClass("ItemStack");
       Object packet = null;
       Object item = null;
       try {
           item = ReflectionUtil.getBukkitClass("inventory.CraftItemStack").getMethod("asNMSCopy", org.bukkit.inventory.ItemStack.class).invoke(item, itemStack);
           packet = PacketPlayOutEntityEquipment.getConstructor(new Class<?>[] {int.class, int.class, ItemStack}).newInstance(getID(), action.getValue(), item);
       } catch (IllegalArgumentException e) {
           e.printStackTrace();
       } catch (SecurityException e) {
           e.printStackTrace();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       } catch (NoSuchMethodException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return packet;
    }

    public Object getEntityMetaPacket() {
        Class<?> PacketPlayOutEntityMetadata = ReflectionUtil.getCraftClass("PacketPlayOutEntityMetadata");
        Class<?> DataWatcher = ReflectionUtil.getCraftClass("DataWatcher");
        Object packet = null;
        try {
            packet = PacketPlayOutEntityMetadata.getConstructor(new Class<?>[]{int.class, DataWatcher, boolean.class}).newInstance(getID(), getWatcher(), true);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packet;
    }

    public Object getWatcher() {
        Class<?> Entity = ReflectionUtil.getCraftClass("Entity");
        Class<?> DataWatcher = ReflectionUtil.getCraftClass("DataWatcher");
        Object watcher = null;
        try {
            watcher = DataWatcher.getConstructor(new Class<?>[] { Entity }).newInstance(entity_player);
            Method a = ReflectionUtil.getMethod(DataWatcher, "a", new Class<?>[] { int.class, Object.class });
            a.invoke(watcher, 6, (Float) 20f);
            a.invoke(watcher, 10, (Byte) (byte) 127);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return watcher;
    }

    public Integer getID() {
        return id;
    }

    private int getFixLocation(double pos){
        return (int) MathHelper.floor(pos * 32.0D);
    }
}