package ru.ItzDarth.DarthRPG.spells.wizard;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import ru.ItzDarth.DarthCore.utils.RParticles;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.SpellEffect;

import de.slikey.effectlib.util.ParticleEffect;

public class FlashIII extends SpellEffect {

    @Override
    public boolean cast(final Player p, final PlayerDataRPG pd, final int level) {
        final Location start = p.getLocation().add(0, p.getEyeHeight() * 0.1, 0);
        Location permStart = p.getLocation().add(0, p.getEyeHeight() * 0.1, 0).clone();
        Location loc = start;
        Vector direction = p.getLocation().getDirection().normalize();
        direction.setY(0);
        int length = 15;
        Location prev = null;
        for (int k = 0; k < length; k++) {
            Location temp = loc.clone();
            loc = loc.add(direction);
            if (!RParticles.isAirlike(loc.getBlock()))
                break;
            prev = temp.clone();
        }
        if(prev != null) {
            RParticles.show(ParticleEffect.EXPLOSION_NORMAL, permStart, 30);
            RParticles.show(ParticleEffect.EXPLOSION_NORMAL, prev, 30);
            p.teleport(prev);
        } else {
            p.sendMessage(ChatColor.RED + "You can't flash through walls!");
            return false;
        }
        Spell.notify(p, "You instantly teleport a long distance.");
        return true;
    }
}
