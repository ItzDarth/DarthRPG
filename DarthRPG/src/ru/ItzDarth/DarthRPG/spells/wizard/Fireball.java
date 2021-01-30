package ru.ItzDarth.DarthRPG.spells.wizard;

import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.metadata.FixedMetadataValue;

import ru.ItzDarth.DarthCore.utils.RMetadata;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.SpellEffect;

public class Fireball extends SpellEffect {

    @Override
    public boolean cast(Player p, PlayerDataRPG pd, int level) {
        SmallFireball fireball = (SmallFireball) p.launchProjectile(SmallFireball.class);
        fireball.setIsIncendiary(false);
        fireball.setShooter(p);
        int damage = pd.getDamage(true);
        damage *= functions[0].applyAsDouble(level) / 100.0;
        fireball.setMetadata(RMetadata.META_DAMAGE, new FixedMetadataValue(Spell.plugin, damage));
        Spell.notify(p, "You shoot off a fireball.");
        return true;
    }

}
