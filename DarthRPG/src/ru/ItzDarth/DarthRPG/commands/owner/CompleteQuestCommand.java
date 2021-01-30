package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.quests.QuestManager;

public class CompleteQuestCommand extends RPGAbstractCommand {

    public CompleteQuestCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(final Player p, PlayerDataRPG pd, String[] args) {
        if (!QuestManager.quests.containsKey(args[0])) {
            pd.sendMessage("quest with id " + args[0] + " does not exist");
            return;
        }
        pd.questProgress.put(args[0], 1000);
        p.sendMessage("Added completed quest: " + args[0]);
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
