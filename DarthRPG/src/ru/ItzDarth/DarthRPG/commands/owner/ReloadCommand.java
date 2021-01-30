package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class ReloadCommand extends RPGAbstractCommand {

    public ReloadCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.getServer().reload();
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {

    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
