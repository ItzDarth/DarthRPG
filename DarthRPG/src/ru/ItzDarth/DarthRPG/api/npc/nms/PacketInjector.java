package ru.ItzDarth.DarthRPG.api.npc.nms;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class PacketInjector {

    private Field EntityPlayer_playerConnection;
    private Class<?> PlayerConnection;
    private Field PlayerConnection_networkManager;

    private Class<?> NetworkManager;
    private Field a;
    private Field b;

    public PacketInjector() {
        try {
            EntityPlayer_playerConnection = ReflectionUtil.getField(ReflectionUtil.getCraftClass("EntityPlayer"), "playerConnection");
            PlayerConnection = ReflectionUtil.getCraftClass("PlayerConnection");
            PlayerConnection_networkManager = ReflectionUtil.getField(PlayerConnection, "networkManager");
            NetworkManager = ReflectionUtil.getCraftClass("NetworkManager");
            a = ReflectionUtil.getField(NetworkManager, "b");
            b = ReflectionUtil.getField(NetworkManager, "channel");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void addPlayer(Player p) {
        try {
            Channel ch = getChannel(getNetworkManager(ReflectionUtil.getHandle(p)));
            if (ch.pipeline().get("PacketInjector") == null) {
                PacketReader h = new PacketReader(p);
                ch.pipeline().addBefore("packet_handler", "PacketInjector", h);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void removePlayer(Player p) {
        try {
            Channel ch = getChannel(getNetworkManager(ReflectionUtil.getHandle(p)));
            if(ch.pipeline().get("PacketInjector") != null) {
                ch.pipeline().remove("PacketInjector");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private Object getNetworkManager(Object ep) {
        try {
            return ReflectionUtil.getFieldValue(PlayerConnection_networkManager, EntityPlayer_playerConnection.get(ep));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Channel getChannel(Object networkManager) {
        Channel ch = null;
        try {
            ch = ReflectionUtil.getFieldValue(a, networkManager);
        } catch (Exception e) {
            ch = ReflectionUtil.getFieldValue(b, networkManager);
        }
        return ch;
    }
}