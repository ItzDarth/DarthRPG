package ru.ItzDarth.DarthRPG.mobs.spells;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RParticles;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.SakiRPG;
import ru.ItzDarth.DarthRPG.mobs.MobData;

import de.slikey.effectlib.util.ParticleEffect;

public class ManaDrainSpell extends MobSpell {

    private int amount;

    private long cooldown;

    public ManaDrainSpell(int amount, long cooldown) {
        this.amount = amount;
        this.cooldown = cooldown;
    }

    public void castSpell(final LivingEntity caster, final MobData md, Player target) {
        if (SakiRPG.plugin.getPD(target) != null) {
            PlayerDataRPG pd = SakiRPG.plugin.getPD(target);
            pd.mana -= amount;
            if (pd.mana < 0) {
                pd.mana = 0;
                pd.updateHealthManaDisplay();
                RParticles.showWithOffset(ParticleEffect.CRIT_MAGIC, target.getLocation(), 2.0, 25);
            }
        }
    }

    @Override
    public long getCastDelay() {
        return cooldown;
    }
}
