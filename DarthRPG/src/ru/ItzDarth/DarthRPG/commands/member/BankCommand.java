package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class BankCommand extends RPGAbstractCommand {

    public BankCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        pd.openBank();
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
