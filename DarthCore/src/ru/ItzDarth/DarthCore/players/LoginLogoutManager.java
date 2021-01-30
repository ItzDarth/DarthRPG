package ru.ItzDarth.DarthCore.players;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.ItzDarth.DarthCore.AbstractManagerCore;
import ru.ItzDarth.DarthCore.PlayerData;
import ru.ItzDarth.DarthCore.SakiCore;
import ru.ItzDarth.DarthCore.commands.owner.MaintenanceCommand;
import ru.ItzDarth.DarthCore.motd.MotdManager;

public class LoginLogoutManager extends AbstractManagerCore {

    public LoginLogoutManager(SakiCore plugin) {
        super(plugin);
    }

    @Override
    public void initialize() {
        for (Player p : plugin.getServer().getOnlinePlayers())
            login(p);
    }

    @EventHandler
    public void onAsyncPrelogin(AsyncPlayerPreLoginEvent event) {
        if (MaintenanceCommand.maintenanceMode && !MaintenanceCommand.allowed.contains(event.getUniqueId().toString())) {
            if (MotdManager.getMotd() != null)
                event.disallow(Result.KICK_OTHER, MotdManager.getMotd());
            else
                event.disallow(Result.KICK_OTHER, ChatColor.RED + "Zentrela is down for maintenance. Please check the MOTD (server list) for details!");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        login(p);
        event.setJoinMessage("");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        logoff(p);
        event.setQuitMessage("");
    }

    public void login(Player p) {
        System.out.println("login player " + p);
        plugin.addPD(p);
    }

    public void logoff(Player p) {
        PlayerData pd = plugin.removePD(p);
        if (pd != null) {
            pd.quit();
        }
    }

}
