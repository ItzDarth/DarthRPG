package ru.ItzDarth.DarthRPG.commands.beta;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.ItzDarth.DarthCore.utils.RTicks;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class NightVisionCommand extends RPGAbstractCommand {
    
    public NightVisionCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, RTicks.seconds(600), 1), false);
        p.sendMessage("Gave you night vision.");
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
