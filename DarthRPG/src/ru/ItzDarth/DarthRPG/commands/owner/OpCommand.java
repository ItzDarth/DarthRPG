package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class OpCommand extends RPGAbstractCommand {

    public OpCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        String s = args[0];
        Player target = plugin.getServer().getPlayerExact(s);
        if (target != null && target.isOnline() && plugin.getPD(target) != null) {
            if (target.isOp()) {
                p.sendMessage(target.getName() + " is already op.");
            } else {
                target.setOp(true);
                p.sendMessage("Opped " + target.getName() + ".");
                target.sendMessage("You were given op by " + p.getName() + ".");
            }
        } else {
            p.sendMessage(ChatColor.RED + "Could not find online player '" + s + "'.");
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
