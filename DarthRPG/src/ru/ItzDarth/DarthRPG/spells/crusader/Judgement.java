package ru.ItzDarth.DarthRPG.spells.crusader;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RMath;
import ru.ItzDarth.DarthCore.utils.RParticles;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.SpellEffect;

public class Judgement extends SpellEffect {

    @Override
    public boolean cast(final Player p, PlayerDataRPG pd, int level) {
        int damage = pd.getDamage(true);
        switch (level) {
            case 1:
                damage *= 2.50;
                break;
            case 2:
                damage *= 2.75;
                break;
            case 3:
                damage *= 3.00;
                break;
            case 4:
                damage *= 3.25;
                break;
            case 5:
                damage *= 3.50;
                break;
        }
        for (Entity e : RMath.getNearbyEntitiesCylinder(p.getLocation(), 17, 9)) {
            if (e == p)
                continue;
            if (Spell.damageEntity(e, damage, p, true, false))
                RParticles.sendLightning(p, e.getLocation());
        }
        Spell.notify(p, "The skies rumble as bolts of lightning shoot downwards.");
        return true;
    }

}
