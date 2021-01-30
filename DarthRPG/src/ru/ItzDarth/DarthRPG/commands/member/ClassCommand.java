package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.SakiRPG;
import ru.ItzDarth.DarthRPG.classes.ClassManager;
import ru.ItzDarth.DarthRPG.classes.ClassType;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class ClassCommand extends RPGAbstractCommand {

    public ClassCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        if (p.getWorld().getName().equalsIgnoreCase(SakiRPG.TUTORIAL_WORLD)) {
            p.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "Sorry! You can't use this command in the tutorial!");
            p.sendMessage(ChatColor.GRAY + "> " + ChatColor.AQUA + "Please finish the tutorial first. Feel free to ask for help!");
            return;
        }
        if (pd.classType == ClassType.VILLAGER)
            ClassManager.showAllClassMenu(p, pd);
        else
            ClassManager.showClassSpellMenu(p, pd);
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
