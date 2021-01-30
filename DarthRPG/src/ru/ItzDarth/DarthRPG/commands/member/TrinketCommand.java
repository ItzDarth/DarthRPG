package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.trinkets.TrinketManager;

public class TrinketCommand extends RPGAbstractCommand {

    public TrinketCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        TrinketManager.showMenu(p, pd);
        pd.usedTrinketCommand = true;
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
