package ru.ItzDarth.DarthRPG.api.darthrpg.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KillMobEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private Entity entity;
	 
	public KillMobEvent(Player killer, Entity entity) {
	    this.player = killer;
	    this.entity = entity;
	}
	 
	public Player getPlayer() {
	    return this.player;
	}
	 
	public Entity getEntity() {
	    return this.entity;
	}
	
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
}