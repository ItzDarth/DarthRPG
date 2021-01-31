package ru.ItzDarth.DarthRPG.api.npc;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import ru.ItzDarth.DarthRPG.api.npc.enums.EventAction;

public class NPCInteractEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private NPC npc;
    private EventAction action;

    public NPCInteractEvent(Player player, NPC npc, EventAction action) {
        this.player = player;
        this.npc = npc;
        this.action = action;
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNpc() {
        return npc;
    }

    public EventAction getAction() {
        return action;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}