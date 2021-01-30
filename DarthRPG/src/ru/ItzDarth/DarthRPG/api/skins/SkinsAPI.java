package ru.ItzDarth.DarthRPG.api.skins;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

import ru.ItzDarth.DarthRPG.api.skins.exceptions.InsupportedVersionException;
import ru.ItzDarth.DarthRPG.api.skins.nms.Skins_1_12_R1;
import ru.ItzDarth.DarthRPG.api.skins.skins.SkinsManager;

public class SkinsAPI{

    private static Plugin plugin;

    public static SkinsManager skinsManager;
    private static boolean enabled;

    private SkinsAPI(){}

    public static void setPlugin(Plugin plugin){
        Validate.notNull(plugin, "Plugin cannot be null");
        SkinsAPI.plugin = plugin;
    }

    public static void enable(){

        if(plugin == null) return;

        Validate.isTrue(!enabled, "Plugin is already enabled");

        try {
            if(setupSkins()){
                enabled = true;
            }else{
                enabled = false;
            }
        } catch (InsupportedVersionException e) {
            e.printStackTrace();
        }

    }

    public void disable(){
        Validate.isTrue(enabled, "Plugin is already disabled");
        enabled = false;
    }

    private static boolean setupSkins() throws InsupportedVersionException {
        skinsManager = new SkinsManager(new Skins_1_12_R1());
        return true;
    }

    public static Plugin getPlugin(){
        return plugin;
    }

}
