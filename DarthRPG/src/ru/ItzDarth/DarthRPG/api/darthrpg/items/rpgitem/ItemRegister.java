package ru.ItzDarth.DarthRPG.api.darthrpg.items.rpgitem;

import java.util.ArrayList;
import java.util.List;

import ru.ItzDarth.DarthRPG.api.darthrpg.items.indetifications.Indetification;

public class ItemRegister {
	
	public int PHYSICAL = 0;
	public int EARTH = 0;
	public int THUNDER = 0;
	public int WATER = 0;
	public int FIRE = 0;
	public int AIR = 0;
	
	public List<Indetification> INDETIFICATIONS = new ArrayList<Indetification>();
	
	public ItemRegister(String[][] damages, String[][] indetifications) {
		for(String[] key : damages) {
			String name = key[0];
			int damage = Integer.parseInt(key[1]);
			switch(name) {
				case "P": {
					PHYSICAL = damage;
					break;
				}
				case "E": {
					EARTH = damage;
					break;
				}
				case "T": {
					THUNDER = damage;
					break;
				}
				case "W": {
					WATER = damage;
					break;
				}
				case "F": {
					FIRE = damage;
					break;
				}
				case "A": {
					AIR = damage;
					break;
				}
			}
		}
		for(String[] indetification : indetifications) {
			INDETIFICATIONS.add(new Indetification(indetification[0], Integer.parseInt(indetification[1])));
		}
	}
	
}
