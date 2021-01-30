package ru.ItzDarth.DarthRPG.holograms;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import ru.ItzDarth.DarthCore.utils.RScheduler;
import ru.ItzDarth.DarthCore.utils.RTags;
import ru.ItzDarth.DarthCore.utils.RTicks;
import ru.ItzDarth.DarthRPG.npcs.NPCManager;

public class Hologram {

    public Location loc;
    public ArmorStand as = null;
    public String name;

    public Hologram(String name, Location loc) {
        this.name = name;
        this.loc = loc;
    }

    public void despawn() {
        if (as != null)
            as.remove();
        as = null;
    }

    public boolean spawn() {
        if (loc.getChunk().isLoaded()) {
            as = RTags.makeStand(name, loc, true);
            RScheduler.schedule(NPCManager.plugin, new Runnable() {
                public void run() {
                    if (loc.getChunk().isLoaded() && as != null && as.isValid()) {
                        as.teleport(loc);
                    }
                }
            }, RTicks.seconds(1));
            return true;
        }
        return false;
    }

}
