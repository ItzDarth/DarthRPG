package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.commands.mod.TeleportCommand;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class TPHideCommand extends RPGAbstractCommand {

    public TPHideCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {

        if (TeleportCommand.noTP.contains(p.getName())) {
            p.sendMessage("ppl can tp to you now.");
            TeleportCommand.noTP.remove(p.getName());
        } else {
            p.sendMessage("ppl can't tp to you anymore!");
            TeleportCommand.noTP.add(p.getName());
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
