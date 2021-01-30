package ru.ItzDarth.DarthCore.commands.mod;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.PlayerData;
import ru.ItzDarth.DarthCore.commands.AbstractCommand;

public class FlyCommand extends AbstractCommand {

    public FlyCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerData pd, String[] args) {
        p.setAllowFlight(!p.getAllowFlight());
        p.sendMessage(ChatColor.AQUA + "Flying " + (p.getAllowFlight() ? "en" : "dis") + "abled!");
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
