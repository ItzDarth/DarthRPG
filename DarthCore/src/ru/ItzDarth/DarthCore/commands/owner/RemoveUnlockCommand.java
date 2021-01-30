package ru.ItzDarth.DarthCore.commands.owner;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.PlayerData;
import ru.ItzDarth.DarthCore.commands.AbstractCommand;
import ru.ItzDarth.DarthCore.unlocks.Unlock;

public class RemoveUnlockCommand extends AbstractCommand {

    public RemoveUnlockCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerData pd, String[] args) {
        if (args.length != 2) {
            p.sendMessage(ChatColor.RED + "Use as /removeunlock <name> <unlock>");
        } else if (args.length == 2) {
            Unlock unlock = Unlock.valueOf(args[1].toUpperCase());
            Player p2 = plugin.getServer().getPlayer(args[0]);
            if (p2 != null && p2.isValid() && p2.isOnline()) {
                PlayerData pd2 = plugin.getPD(p2);
                pd2.removeUnlock(unlock);
                p.sendMessage("Removed unlock " + unlock + " from " + pd2.getName());
            } else {
                p.sendMessage("User is not online.");
            }
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Use as /removeunlock <name> <unlock>");
        } else if (args.length == 2) {
            Unlock unlock = Unlock.valueOf(args[1].toUpperCase());
            Player p2 = plugin.getServer().getPlayer(args[0]);
            if (p2 != null && p2.isValid() && p2.isOnline()) {
                PlayerData pd2 = plugin.getPD(p2);
                pd2.removeUnlock(unlock);
                sender.sendMessage("Removed unlock " + unlock + " from " + pd2.getName());
            } else {
                sender.sendMessage("User is not online.");
            }
        }
    }

}
