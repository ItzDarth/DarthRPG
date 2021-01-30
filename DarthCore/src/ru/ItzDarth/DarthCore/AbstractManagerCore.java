package ru.ItzDarth.DarthCore;

public abstract class AbstractManagerCore extends AbstractManager {

    public static SakiCore plugin;

    public AbstractManagerCore(SakiCore pl) {
        super(pl);
        plugin = pl;
    }

}
