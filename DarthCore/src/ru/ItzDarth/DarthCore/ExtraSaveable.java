package ru.ItzDarth.DarthCore;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.players.PlayerDataFile;

public interface ExtraSaveable {

    public void preLoad(Player p);

    public void postLoad(Player p);

    public PlayerDataFile extraSave(PlayerDataFile pdf);
    
    public void setExtraLoadedData(boolean isNew, PlayerDataFile pdf);
}
