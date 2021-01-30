package ru.ItzDarth.DarthRPG.shield;

import java.util.List;

import ru.ItzDarth.DarthCore.SakiCore;
import ru.ItzDarth.DarthCore.shield.SakiShield;
import ru.ItzDarth.DarthCore.shield.ShieldCheck;
import ru.ItzDarth.DarthRPG.SakiRPG;

public class SakiShieldRPG extends SakiShield {

    @Override
    public SakiCore getPlugin() {
        return SakiRPG.plugin;
    }

    @Override
    public List<ShieldCheck> getChecks() {
        return null;
    }

    @Override
    public void halt() {
        //        getPlugin()
    }

}
