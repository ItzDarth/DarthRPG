package ru.ItzDarth.DarthRPG.commands.beta;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.items.ItemManager;
import ru.ItzDarth.DarthRPG.items.RPGItem;

public class TestPotionsCommand extends RPGAbstractCommand {

    public TestPotionsCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        while (pd.getPlayer().getInventory().firstEmpty() != -1) {
            RPGItem item = ItemManager.itemIdentifierToRPGItemMap.get("hp_potion_4");
            pd.getPlayer().getInventory().addItem(item.generate());
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
