package ru.ItzDarth.DarthRPG.spells.paladin;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RParticles;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.SpellEffect;

import de.slikey.effectlib.util.ParticleEffect;

public class Smash extends SpellEffect {

    @Override
    public boolean cast(final Player p, PlayerDataRPG pd, int level) {
        int damage = pd.getDamage(true);
        damage *= functions[0].applyAsDouble(level) / 100.0;
        RParticles.show(ParticleEffect.EXPLOSION_LARGE, p.getLocation(), 5);
        Spell.damageNearby(damage, p, p.getLocation(), 3, new ArrayList<Entity>());
        Spell.notify(p, "You smash the ground with a powerful blow.");
        return true;
    }

}
