package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ru.ItzDarth.DarthCore.motd.MotdManager;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class MotdCommand extends RPGAbstractCommand implements Listener {

    public MotdCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("refresh")) {
                MotdManager.fetchMotd();
                sender.sendMessage("Refreshing motd.");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s);
            sb.append(" ");
        }
        String motd = sb.toString().trim().replace("[newline]", "\n");
        sender.sendMessage("Set motd to: " + motd);
        MotdManager.updateMotd(motd);
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {

    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
