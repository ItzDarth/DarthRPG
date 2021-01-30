package ru.ItzDarth.DarthCore.shield;

import ru.ItzDarth.DarthCore.AbstractManagerCore;
import ru.ItzDarth.DarthCore.SakiCore;

public class ShieldManager extends AbstractManagerCore {

    private SakiShield activeShield = null;

    public ShieldManager(SakiCore plugin) {
        super(plugin);
    }

    @Override
    public void initialize() {
        register(new SakiShieldCore());
    }

    public void register(SakiShield shield) {
        if (this.activeShield != null) {
            activeShield.halt();
        }
        activeShield = shield;
        shield.start();
    }

}
