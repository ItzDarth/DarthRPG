package ru.ItzDarth.DarthRPG.regions.areas.actions;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.regions.areas.TriggerAreaAction;

public class TriggerAreaActionState extends TriggerAreaAction {

    private String state = "";

    public TriggerAreaActionState(String state) {
        this.state = state;
    }

    @Override
    public void act(Player p, PlayerDataRPG pd) {
        pd.addState(state);
    }

}
