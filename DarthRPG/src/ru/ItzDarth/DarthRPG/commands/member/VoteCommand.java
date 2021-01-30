package ru.ItzDarth.DarthRPG.commands.member;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthCore.utils.fanciful.FancyMessage;
import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class VoteCommand extends RPGAbstractCommand {

    public VoteCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        p.sendMessage("");
        FancyMessage fm = new FancyMessage("> ");
        fm.color(ChatColor.GRAY);
        fm.link("http://zentrela.net/vote.html");
        fm.then("You can earn Reward Points by voting!");
        fm.color(ChatColor.GREEN);
        fm.link("http://zentrela.net/vote.html");
        fm.then(" Click here for a link.");
        fm.color(ChatColor.YELLOW);
        fm.link("http://zentrela.net/vote.html");
        fm.send(p);
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
