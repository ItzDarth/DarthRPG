package ru.ItzDarth.DarthCore.shield;

import java.util.List;

import ru.ItzDarth.DarthCore.SakiCore;

public abstract class SakiShield {

    public void start() {
        int count = 0;
        List<ShieldCheck> checks = getChecks();
        if (checks != null) {
            for (ShieldCheck sc : checks) {
                getPlugin().getServer().getPluginManager().registerEvents(sc, getPlugin());
                count++;
            }
        }
        System.out.println("Registered " + count + " checks for SakiShield.");
    }

    public abstract SakiCore getPlugin();

    public abstract List<ShieldCheck> getChecks();

    public abstract void halt();
}
