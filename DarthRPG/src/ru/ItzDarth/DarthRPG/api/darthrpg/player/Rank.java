package ru.ItzDarth.DarthRPG.api.darthrpg.player;

public enum Rank {
	
	PLAYER("§7", "§7", 8),
	
	VIP("§aVIP", "§aVIP", 7),
	VIPPLUS("§aVIP§6+", "§aVIP§6+", 6),
	HERO("§dHERO", "§dHERO", 5),
	CHAMPION("§eCHAMPION", "§eCHAMPION", 4),
	
	BUILDER("§eBUILDER", "§eBUILDER", 3),
	
	MODER("§9MOD", "§9MOD", 3),
	MODPLUS("§9MOD+", "§9MOD+", 2),
	ADMIN("§cADMIN", "§cADMIN", 1);
	
	private String prefix,fullprefix;
	private int priority;
	
	Rank(String prefix, String fullprefix, int priority) {
		this.prefix = prefix;
		this.fullprefix = fullprefix;
		this.priority = priority;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getFullPrefix() {
		return fullprefix;
	}
	
	public int getPriority() {
		return priority;
	}
	
}
