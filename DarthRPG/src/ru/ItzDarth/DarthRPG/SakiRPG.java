package ru.ItzDarth.DarthRPG;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;

import ru.ItzDarth.DarthCore.PlayerData;
import ru.ItzDarth.DarthCore.SakiCore;
import ru.ItzDarth.DarthCore.options.SakiOption;
import ru.ItzDarth.DarthCore.pets.PetManager;
import ru.ItzDarth.DarthCore.utils.RScheduler;
import ru.ItzDarth.DarthCore.utils.RTicks;
import ru.ItzDarth.DarthRPG.buffs.BuffManager;
import ru.ItzDarth.DarthRPG.buycraft.BuycraftManager;
import ru.ItzDarth.DarthRPG.chat.ChatManager;
import ru.ItzDarth.DarthRPG.combat.CombatManager;
import ru.ItzDarth.DarthRPG.commands.CommandManager;
import ru.ItzDarth.DarthRPG.commands.RPGAbstractCommand;
import ru.ItzDarth.DarthRPG.drops.DropManager;
import ru.ItzDarth.DarthRPG.dungeons.DungeonManager;
import ru.ItzDarth.DarthRPG.economy.EconomyManager;
import ru.ItzDarth.DarthRPG.economy.ShardManager;
import ru.ItzDarth.DarthRPG.fakes.FakeManager;
import ru.ItzDarth.DarthRPG.fun.GrappleManager;
import ru.ItzDarth.DarthRPG.general.EnvironmentManager;
import ru.ItzDarth.DarthRPG.general.SchematicManager;
import ru.ItzDarth.DarthRPG.general.StealthManager;
import ru.ItzDarth.DarthRPG.guilds.GuildManager;
import ru.ItzDarth.DarthRPG.haybales.HaybaleManager;
import ru.ItzDarth.DarthRPG.help.HelpManager;
import ru.ItzDarth.DarthRPG.holograms.HologramManager;
import ru.ItzDarth.DarthRPG.horses.HorseManager;
import ru.ItzDarth.DarthRPG.items.ItemManager;
import ru.ItzDarth.DarthRPG.mobs.EntityRegistrar;
import ru.ItzDarth.DarthRPG.mobs.MobData;
import ru.ItzDarth.DarthRPG.mobs.MobManager;
import ru.ItzDarth.DarthRPG.music.MusicManager;
import ru.ItzDarth.DarthRPG.npcs.NPCManager;
import ru.ItzDarth.DarthRPG.npcs.generics.GenericNPCManager;
import ru.ItzDarth.DarthRPG.particles.ParticleManager;
import ru.ItzDarth.DarthRPG.parties.PartyManager;
import ru.ItzDarth.DarthRPG.quests.QuestManager;
import ru.ItzDarth.DarthRPG.rebirths.RebirthManager;
import ru.ItzDarth.DarthRPG.regions.RegionManager;
import ru.ItzDarth.DarthRPG.rewards.RewardsManager;
import ru.ItzDarth.DarthRPG.shops.ShopManager;
import ru.ItzDarth.DarthRPG.skills.SkillManager;
import ru.ItzDarth.DarthRPG.soaring.SoaringManager;
import ru.ItzDarth.DarthRPG.spells.SpellManager;
import ru.ItzDarth.DarthRPG.tips.TipManager;
import ru.ItzDarth.DarthRPG.top.TopManager;
import ru.ItzDarth.DarthRPG.trades.TradeManager;
import ru.ItzDarth.DarthRPG.trinkets.TrinketManager;
import ru.ItzDarth.DarthRPG.utils.gson.RPGItemAdapter;
import ru.ItzDarth.DarthRPG.vip.BoostManager;
import ru.ItzDarth.DarthRPG.votes.VoteManager;
import ru.ItzDarth.DarthRPG.warps.WarpManager;
import ru.ItzDarth.DarthRPG.worldboss.WorldBossManager;

public class SakiRPG extends SakiCore {

    public static final String GAME_WORLD = "main";
    public static final String LOBBY_WORLD = "lobby";
    public static final String TUTORIAL_WORLD = "tutorial";
    public static final String BOSS_WORLD = "worldboss";

    public static SakiRPG plugin;

    private static Location tutorialSpawnLoc = null;

    public static Location getTutorialSpawn() {
        if (tutorialSpawnLoc != null)
            return tutorialSpawnLoc;
        World w = plugin.getServer().getWorld(SakiRPG.TUTORIAL_WORLD);
        return tutorialSpawnLoc = new Location(w, -270.5, 63.0, -372.5, 0f, 0f);
    }

    @Override
    public void onEnable() {
        plugin = this;
        AbstractManagerRPG.plugin = this;
        RPGAbstractCommand.plugin = this;
        setPlayerdataClass(PlayerDataRPG.class);
        RPGItemAdapter.register();

        new DropManager(this); // before loadWorlds
        loadWorlds();
        MobData.plugin = this;
        EntityRegistrar.registerEntities();

        File f = getDataFolder();
        if (!f.exists())
            f.mkdirs();

        getServer().createWorld(new WorldCreator(LOBBY_WORLD));
        getServer().createWorld(new WorldCreator(GAME_WORLD));
        getServer().createWorld(new WorldCreator(TUTORIAL_WORLD));
        getServer().createWorld(new WorldCreator("dungeon"));

        new EnvironmentManager(this);
        new CommandManager(this);

        this.unloadManager(ru.ItzDarth.DarthCore.chat.ChatManager.class); // use custom chat manager for RPG
        new ChatManager(this);
        new CombatManager(this);
        new ItemManager(this); //must be before mobmanager
        new MobManager(this);
        new GrappleManager(this);
        new SpellManager(this);
        new WarpManager(this);
        new StealthManager(this);
        new EconomyManager(this);
        new ShardManager(this);
        new SchematicManager(this);
        //        new MobBossManager(this);
        new RegionManager(this);
        new NPCManager(this);
        new QuestManager(this);
        //        new PacketManager(this);
        new BuffManager(this);
        new GuildManager(this);
        new TradeManager(this);
        new PartyManager(this);
        new TipManager(this);
        new ShopManager(this);
        new TrinketManager(this);
        new ParticleManager(this);
        new HologramManager(this);
        if (!SakiRPG.TEST_REALM)
            new VoteManager(this);
        new RewardsManager(this);
        new BuycraftManager(this);
        new HelpManager(this);
        new DungeonManager(this);
        new HaybaleManager(this);
        new HorseManager(this);
        new BoostManager(this);
        new RebirthManager(this);
        new MusicManager(this);
        new GenericNPCManager(this);
        new SkillManager(this);
        new PetManager(this);
        new FakeManager(this);
        new SoaringManager(this);
        new WorldBossManager(this);
        new TopManager(this);

        System.out.println("Successfully loaded SakiRPG.");

        postLoad();
    }

    public void postLoad() {
        RScheduler.schedule(plugin, new Runnable() {
            public void run() {
                for (World w : getServer().getWorlds()) {
                    for (Chunk chunk : w.getLoadedChunks()) {
                        NPCManager.handleChunk(chunk);
                        DungeonManager.handleChunk(chunk);
                        HologramManager.handleChunk(chunk);
                        EnvironmentManager.handleChunk(chunk);
                    }
                }
            }
        }, RTicks.seconds(1));
        RScheduler.schedule(plugin, new Runnable() {
            public void run() {
                for (PlayerData pd : getAllPlayerData()) {
                    if (!(pd instanceof PlayerDataRPG))
                        continue;
                    PlayerDataRPG pdr = (PlayerDataRPG) pd;
                    if (pdr.getOption(SakiOption.SOFT_LAUNCH)) {
                        pd.sendMessage(ChatColor.GRAY + "> The world is incomplete and we are still building stuff!");
                        pd.sendMessage(ChatColor.GRAY + "> We are working hard to get more things out soon!");
                        pd.sendMessage(ChatColor.GRAY + "> Keep an eye on the forums at zentrela.net for updates.");
                        pd.sendMessage(ChatColor.GRAY + "> Hide this message in /options (end of the second row).");
                    }
                }
                RScheduler.schedule(plugin, this, RTicks.seconds(300));
            }
        }, RTicks.seconds(5));
    }

    @Override
    public void onDisable() {
        try {
            WarpManager.saveWarps();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            EntityRegistrar.unregisterEntities();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ParticleManager.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadWorlds() {
        for (World w : getServer().getWorlds()) {
            EnvironmentManager.despawnEntities(w.getEntities().toArray(new Entity[w.getEntities().size()]));
            w.setThunderDuration(0);
            w.setWeatherDuration(0);
            w.setStorm(false);
            w.setThundering(false);
            if (!w.getName().equals(BOSS_WORLD))
                w.setTime(0);
        }
    }

    @Override
    public PlayerDataRPG getPD(Object o) {
        return (PlayerDataRPG) super.getPD(o);
    }
}
