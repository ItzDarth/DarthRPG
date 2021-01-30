package ru.ItzDarth.DarthCore.commands.general;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.PlayerData;
import ru.ItzDarth.DarthCore.commands.AbstractCommand;

public class PetCommand extends AbstractCommand {

    public PetCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerData pd, String[] args) {
        //        plugin.getInstance(PetManager.class).showMenu(p, pd);
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
