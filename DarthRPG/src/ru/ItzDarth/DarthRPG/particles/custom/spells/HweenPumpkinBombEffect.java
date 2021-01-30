package ru.ItzDarth.DarthRPG.particles.custom.spells;

import org.bukkit.Color;
import org.bukkit.Location;

import ru.ItzDarth.DarthCore.utils.RMath;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;

public class HweenPumpkinBombEffect extends Effect {

    private Location loc;

    public HweenPumpkinBombEffect(EffectManager effectManager, Location loc) {
        super(effectManager);
        type = EffectType.INSTANT;
        this.loc = loc;
        setLocation(loc);
    }

    @Override
    public void onRun() {
        double x, y, z;
        for (int i = 0; i < 50; i++) {
            int count = 20;
            do {
                count--;
                x = RMath.randDouble(0, 2);
                y = RMath.randDouble(0, 2);
                z = RMath.randDouble(0, 2);
            } while (count >= 0 && x * x + y * y + z * z > 4);
            x -= 1;
            z -= 1;
            loc.add(x, y, z);
            display(ParticleEffect.REDSTONE, loc, Color.ORANGE);
            loc.subtract(x, y, z);
        }
    }

}