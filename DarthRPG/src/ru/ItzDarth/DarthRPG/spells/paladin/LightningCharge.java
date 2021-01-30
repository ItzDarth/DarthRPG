package ru.ItzDarth.DarthRPG.spells.paladin;

import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RParticles;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.spells.Spell;
import ru.ItzDarth.DarthRPG.spells.SpellEffect;

public class LightningCharge extends SpellEffect {

    public static final String BUFF_ID = "lightning charge";
    
    @Override
    public boolean cast(final Player p, PlayerDataRPG pd, int level) {
        double multiplier = 1.0;
        switch (level) {
            case 1:
                multiplier = 1.2;
                break;
            case 2:
                multiplier = 1.4;
                break;
            case 3:
                multiplier = 1.6;
                break;
            case 4:
                multiplier = 1.8;
                break;
            case 5:
                multiplier = 2.0;
                break;
        }
        RParticles.sendLightning(p, p.getLocation());
        pd.giveBuff(LightningCharge.BUFF_ID, multiplier, 6000);
        Spell.notify(p, "You charge your mace with the power of lightning.");
        Spell.notifyDelayed(p, "Lightning charge has worn off.", 6);
        return true;
    }

}
