package ru.ItzDarth.DarthRPG.commands.beta;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.PlayerDataRPG;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.mobs.MobManager;
import ru.ItzDarth.DarthRPG.mobs.MobType;
@SuppressWarnings("deprecation")
public class MobsCommand extends RPGAbstractCommand {
    
    private HashMap<String, Long> last = new HashMap<String, Long>();

    public MobsCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public void executePlayer(Player p, PlayerDataRPG pd, String[] args) {
        if (last.containsKey(p.getName())) {
            if (System.currentTimeMillis() - last.get(p.getName()) < 1000) {
                p.sendMessage("You can only use this command once every second.");
                return;
            }
        }
        last.put(p.getName(), System.currentTimeMillis());
        String name = "test_mob";
        int amount = args.length > 0 ? Integer.parseInt(args[0]) : 1;
        MobType mt = MobManager.mobTypes.get(name);
        MobType mt2 = MobManager.mobTypes.get("test_range");
        if (amount > 10)
            amount = 10;
        for (int k = 0; k < amount; k++) {
            if (Math.random() < 0.5)
                mt.spawn(p.getTargetBlock((HashSet<Byte>) null, 100).getLocation().add(0, 1.25, 0));
            else
                mt2.spawn(p.getTargetBlock((HashSet<Byte>) null, 100).getLocation().add(0, 1.25, 0));
        }
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
