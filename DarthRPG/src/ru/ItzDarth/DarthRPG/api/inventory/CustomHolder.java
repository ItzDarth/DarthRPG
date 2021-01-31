package ru.ItzDarth.DarthRPG.api.inventory;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CustomHolder implements InventoryHolder {
	 
    private final HashMap<Integer, Icon> icons = new HashMap<Integer, Icon>();

    private final int size;
    private final String title;
    private boolean cancelClose = false;

    public CustomHolder(int size, String title) {
        this.size = size;
        this.title = title;
    }
    
    public CustomHolder(int size, String title, boolean cancelClose) {
        this.size = size;
        this.title = title;
        this.cancelClose = cancelClose;
    }

    public void setIcon(int position, Icon icon) {
        this.icons.put(position, icon);
    }
 
    public Icon getIcon(int position) {
        return this.icons.get(position);
    }

    public boolean getCancelClose() {
    	return cancelClose;
    }
    
    public void setCancelClose(boolean cancelClose) {
    	this.cancelClose = cancelClose;
    }
    
    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, this.size, this.title);

        for(Entry<Integer, Icon> entry : this.icons.entrySet()) {
            inventory.setItem(entry.getKey().intValue(), entry.getValue().itemStack);
        }
   
        return inventory;
    }
}