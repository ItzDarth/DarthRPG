package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class LocationCommand extends RPGAbstractCommand {

    public LocationCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        Location loc = p.getLocation();
        double x = roundToHalf(loc.getX());
        double y = roundToHalf(loc.getY());
        double z = roundToHalf(loc.getZ());
        if (args.length > 0) {
            p.sendMessage(ChatColor.GRAY + ">> " + ChatColor.YELLOW + "Current location" + ChatColor.GRAY + ": " + ChatColor.WHITE + x + ", " + y + ", " + z + " [" + roundToHalf(loc.getPitch()) + "p]");
        } else {
            p.sendMessage(ChatColor.GRAY + ">> " + ChatColor.YELLOW + "Current location" + ChatColor.GRAY + ": " + ChatColor.WHITE + x + ", " + y + ", " + z);
        }
    }

    public static double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
