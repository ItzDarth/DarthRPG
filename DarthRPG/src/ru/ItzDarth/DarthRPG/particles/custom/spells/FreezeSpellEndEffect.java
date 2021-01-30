package ru.ItzDarth.DarthRPG.particles.custom.spells;

import org.bukkit.Color;
import org.bukkit.Location;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;

public class FreezeSpellEndEffect extends Effect {

    public float radius = 0.7f;
    public int particles = 20;
    public ParticleEffect particle = ParticleEffect.REDSTONE;

    private Location loc;

    public FreezeSpellEndEffect(EffectManager effectManager, Location loc, int time) {
        super(effectManager);
        type = EffectType.REPEATING;
        period = 10;
        iterations = time * 2;
        this.loc = loc;
    }

    @Override
    public void onRun() {
        double x, z;
        for (double dy = 0; dy < 1.5; dy += 0.25) {
            loc.add(0, dy, 0);
            for (int i = 0; i < particles; i++) {
                double angle = (double) 2 * Math.PI * i / particles;
                x = Math.cos(angle) * radius;
                z = Math.sin(angle) * radius;
                loc.add(x, 0, z);
                display(particle, loc, Color.AQUA);
                loc.subtract(x, 0, z);
            }
            loc.subtract(0, dy, 0);
        }
    }

}