package ru.ItzDarth.DarthRPG.regions.areas.actions;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RSound;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.regions.areas.TriggerAreaAction;

public class TriggerAreaActionPing extends TriggerAreaAction {

    @Override
    public void act(Player p, PlayerDataRPG pd) {
        RSound.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
    }

}
