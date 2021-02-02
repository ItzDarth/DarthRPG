package ru.ItzDarth.DarthRPG.api.darthrpg.player;

public enum Rank {
	
	PLAYER("§7", "§7", 8),
	
	VIP("§aVIP§f ", "§aVIP", 7),
	VIPPLUS("§aVIP§6+§f ", "§aVIP§6+", 6),
	HERO("§dHERO§f ", "§dHERO", 5),
	CHAMPION("§eCHAMPION§f ", "§eCHAMPION", 4),
	
	BUILDER("§eBUILDER§f ", "§eBUILDER", 3),
	
	MODER("§9MOD§f ", "§9MOD", 3),
	MODPLUS("§9MOD+§f ", "§9MOD+", 2),
	ADMIN("§cADMIN§f ", "§cADMIN", 1);
	
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
