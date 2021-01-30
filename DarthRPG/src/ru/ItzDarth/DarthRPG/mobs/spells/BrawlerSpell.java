package ru.ItzDarth.DarthRPG.mobs.spells;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RMath;
import ru.ItzDarth.DarthRPG.mobs.MobData;
import ru.ItzDarth.DarthRPG.spells.Spell;

public class BrawlerSpell extends MobSpell {

    private long cooldown;

    public BrawlerSpell(long cooldown) {
        this.cooldown = cooldown;
    }

    public void castSpell(final LivingEntity caster, final MobData md, Player target) {
        if (Spell.canDamage(target, false) && RMath.flatDistance(target.getLocation(), caster.getLocation()) < 1.5) {
            Spell.damageEntity(target, md.getDamage(), caster, true, false);
        }
    }

    @Override
    public long getCastDelay() {
        return cooldown;
    }
}
