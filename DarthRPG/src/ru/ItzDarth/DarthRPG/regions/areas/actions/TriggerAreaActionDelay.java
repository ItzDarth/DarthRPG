package ru.ItzDarth.DarthRPG.regions.areas.actions;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.regions.areas.TriggerAreaAction;

public class TriggerAreaActionDelay extends TriggerAreaAction {

    public long delay;

    public TriggerAreaActionDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public void act(Player p, PlayerDataRPG pd) {
    }

}
