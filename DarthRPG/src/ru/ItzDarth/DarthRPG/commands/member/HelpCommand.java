package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.help.HelpManager;

public class HelpCommand extends RPGAbstractCommand {

    public HelpCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        HelpManager.openMenu(p);
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
