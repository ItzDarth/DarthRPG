package ru.ItzDarth.DarthRPG.mobs.spells;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RMath;
import ru.ItzDarth.DarthCore.utils.RScheduler;
import ru.ItzDarth.DarthRPG.SakiRPG;
import ru.ItzDarth.DarthRPG.mobs.MobData;

public class TeleportSpell extends MobSpell {

    private long cooldown;
    private long variance;

    public TeleportSpell(long cooldown, long variance) {
        this.cooldown = cooldown;
        this.variance = variance;
    }

    public void castSpell(final LivingEntity caster, final MobData md, Player target) {
        final Location loc = target.getLocation();
        if (RMath.flatDistance(caster.getLocation(), loc) < 50) {
            RScheduler.schedule(SakiRPG.plugin, new Runnable() {
                public void run() {
                    if (target != null && target.isOnline()) {
                        for (Entity e : RMath.getNearbyEntities(loc, 2))
                            if (e instanceof Player)
                                return;
                        md.entity.teleport(loc);
                        md.invuln = System.currentTimeMillis() + 1000;
                    }
                }
            });
        }
    }

    @Override
    public long getCastDelay() {
        return (int) (Math.random() * variance) + cooldown;
    }
}
