package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class RegionCommand extends RPGAbstractCommand {

    public RegionCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        if(pd.region != null) {
           pd.region.sendWelcome(p, null); 
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
