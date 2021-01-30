package ru.ItzDarth.DarthRPG.commands.builder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.general.SchematicManager;

public class BlockLocCommand extends RPGAbstractCommand {

    public BlockLocCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        SchematicManager.giveBlockItem(p);
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
