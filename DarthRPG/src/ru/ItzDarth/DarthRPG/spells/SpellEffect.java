package ru.ItzDarth.DarthRPG.spells;

import java.util.function.IntToDoubleFunction;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;

public abstract class SpellEffect {
    protected IntToDoubleFunction[] functions;

    public abstract boolean cast(Player p, PlayerDataRPG pd, int level);
}
