package ru.ItzDarth.DarthRPG;

import ru.ItzDarth.DarthCore.AbstractManager;

public abstract class AbstractManagerRPG extends AbstractManager {

    public static SakiRPG plugin;

    public AbstractManagerRPG(SakiRPG pl) {
        super(pl);
        plugin = pl;
    }

}
