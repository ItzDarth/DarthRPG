package ru.ItzDarth.DarthRPG.commands.beta;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class GamemodeCommand extends RPGAbstractCommand {

    public GamemodeCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        if (p.getGameMode() == GameMode.ADVENTURE) {
            p.setGameMode(GameMode.CREATIVE);
        } else {
            p.setGameMode(GameMode.ADVENTURE);
        }
        p.sendMessage(ChatColor.GREEN + "You are now in " + p.getGameMode().toString() + ".");
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
