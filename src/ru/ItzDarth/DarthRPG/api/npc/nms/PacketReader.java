package ru.ItzDarth.DarthRPG.api.npc.nms;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import ru.ItzDarth.DarthRPG.api.npc.NPC;
import ru.ItzDarth.DarthRPG.api.npc.NPCInteractEvent;
import ru.ItzDarth.DarthRPG.api.npc.NpcAPI;
import ru.ItzDarth.DarthRPG.api.npc.enums.EventAction;

public class PacketReader extends ChannelDuplexHandler {
    private Player p;

    public PacketReader(final Player p) {
        this.p = p;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
        if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
            Integer id = Integer.parseInt(ReflectionUtil.getFieldValue(packet, "a").toString());
            String action = ReflectionUtil.getFieldValue(packet, "action").toString();
            for (List<NPC> npcd : NpcAPI.getNPCS()) {
                for(NPC npc : npcd) {
                	if (npc.getID().equals(id)) {
                        if (action.equalsIgnoreCase("ATTACK")) {
                            Bukkit.getServer().getPluginManager().callEvent(new NPCInteractEvent(p, npc, EventAction.LEFT_CLICK));
                        } else if (action.equalsIgnoreCase("INTERACT")) {
                            Bukkit.getServer().getPluginManager().callEvent(new NPCInteractEvent(p, npc, EventAction.RIGHT_CLICK));
                        }
                    }
                }
            }
        }
        super.channelRead(context, packet);
    }
}