package ru.ItzDarth.DarthRPG.commands.owner;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;

public class EditNameCommand extends RPGAbstractCommand {

    public EditNameCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.hasItemMeta()) {
            ItemMeta im = item.getItemMeta();
            StringBuilder sb = new StringBuilder();
            for (int k = 1; k < args.length; k++) {
                sb.append(args[k]);
                sb.append(' ');
            }
            String name = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
            im.setDisplayName(name);
            item.setItemMeta(im);
            p.sendMessage("Updated item.");
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
