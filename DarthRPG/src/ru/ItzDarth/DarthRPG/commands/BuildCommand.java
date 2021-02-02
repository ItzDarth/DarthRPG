package ru.ItzDarth.DarthRPG.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.ItzDarth.DarthRPG.api.darthrpg.RPGApi;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.RPGPlayer;
import ru.ItzDarth.DarthRPG.api.darthrpg.player.Rank;
import ru.ItzDarth.DarthRPG.menus.build.BuildGUI;

public class BuildCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		RPGPlayer rp = RPGApi.getRPGPlayer(p);
		if(rp.RANK == Rank.ADMIN || rp.RANK == Rank.BUILDER) {
			new BuildGUI(p);
		}
		
		return true;
	}

}
