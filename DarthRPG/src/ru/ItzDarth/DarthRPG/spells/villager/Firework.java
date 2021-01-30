package ru.ItzDarth.DarthRPG.spells.villager;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RParticles;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.SpellEffect;

public class Firework extends SpellEffect {

    @Override
    public boolean cast(Player p, PlayerDataRPG pd, int level) {
        RParticles.spawnRandomFirework(p.getLocation());
        Spell.notify(p, "Pew pew! A beautiful firework shoots upwards.");
        pd.castedFirework = true;
        return true;
    }
}
