package ru.ItzDarth.DarthRPG.mobs.spells;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RMath;
import ru.ItzDarth.DarthRPG.SakiRPG;
import ru.ItzDarth.DarthRPG.mobs.MobData;

public class DerplaxSnoreSpell extends MobSpell {

    public void castSpell(final LivingEntity caster, final MobData md, Player target) {
        md.shout("Yawwwwnnn....", 30);
        target.sendMessage(ChatColor.GRAY + "> " + ChatColor.WHITE + "You begin to feel tired and sluggish...");
        for (Entity e : RMath.getNearbyEntities(caster.getLocation(), 30)) {
            if (e instanceof Player && SakiRPG.plugin.getPD((Player) e) != null) {
                SakiRPG.plugin.getPD((Player) e).giveSlow(6, 2);
            }
        }
    }

    @Override
    public long getCastDelay() {
        return (int) (Math.random() * 10000) + 15000;
    }
}
