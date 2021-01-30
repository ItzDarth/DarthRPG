package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.RMessages;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.haybales.HaybaleManager;

public class ReloadHayCommand extends RPGAbstractCommand {

    public ReloadHayCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        HaybaleManager.reload();
        sender.sendMessage("Hay reloaded.");
        RMessages.announce(ChatColor.RED + "Hay bales reloaded for updates.");
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {

    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
