package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RMessages;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.horses.HorseManager;

public class ReloadHorsesCommand extends RPGAbstractCommand {

    public ReloadHorsesCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        HorseManager.reload();
        sender.sendMessage("Stables reloaded.");
        RMessages.announce(ChatColor.RED + "Stables reloaded for updates.");
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {

    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
