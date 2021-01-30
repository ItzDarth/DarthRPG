package ru.ItzDarth.DarthRPG.commands;

import ru.ItzDarth.DarthCore.SakiCore;
import ru.ItzDarth.DarthCore.commands.mod.WalkSpeedCommand;
import ru.ItzDarth.DarthCore.players.Rank;
import ru.ItzDarth.DarthRPG.SakiRPG;
import ru.ItzDarth.DarthRPG.commands.admin.SwapCommand;
import ru.ItzDarth.DarthRPG.commands.beta.BaseMobCommand;
import ru.ItzDarth.DarthRPG.commands.beta.ClearNearbyCommand;
import ru.ItzDarth.DarthRPG.commands.beta.EffectsCommand;
import ru.ItzDarth.DarthRPG.commands.beta.GamemodeCommand;
import ru.ItzDarth.DarthRPG.commands.beta.MobsCommand;
import ru.ItzDarth.DarthRPG.commands.beta.NightVisionCommand;
import ru.ItzDarth.DarthRPG.commands.beta.TestEquipsCommand;
import ru.ItzDarth.DarthRPG.commands.beta.TestPotionsCommand;
import ru.ItzDarth.DarthRPG.commands.builder.BlockLocCommand;
import ru.ItzDarth.DarthRPG.commands.builder.BuilderCommand;
import ru.ItzDarth.DarthRPG.commands.builder.SakiReplaceCommand;
import ru.ItzDarth.DarthRPG.commands.builder.TerraformCommand;
import ru.ItzDarth.DarthRPG.commands.builder.VoxelSniperCommand;
import ru.ItzDarth.DarthRPG.commands.builder.WorldEditCommand;
import ru.ItzDarth.DarthRPG.commands.console.AdRewardCommand;
import ru.ItzDarth.DarthRPG.commands.console.BuycraftRewardCommand;
import ru.ItzDarth.DarthRPG.commands.gm.GMCommand;
import ru.ItzDarth.DarthRPG.commands.gm.NPCCommand;
import ru.ItzDarth.DarthRPG.commands.member.BankCommand;
import ru.ItzDarth.DarthRPG.commands.member.ClassCommand;
import ru.ItzDarth.DarthRPG.commands.member.ClearCommand;
import ru.ItzDarth.DarthRPG.commands.member.FlightCommand;
import ru.ItzDarth.DarthRPG.commands.member.GrappleCommand;
import ru.ItzDarth.DarthRPG.commands.member.GuildCommand;
import ru.ItzDarth.DarthRPG.commands.member.HelpCommand;
import ru.ItzDarth.DarthRPG.commands.member.HorseCommand;
import ru.ItzDarth.DarthRPG.commands.member.IgnoreCommand;
import ru.ItzDarth.DarthRPG.commands.member.InfoCommand;
import ru.ItzDarth.DarthRPG.commands.member.LocationCommand;
import ru.ItzDarth.DarthRPG.commands.member.LookupCommand;
import ru.ItzDarth.DarthRPG.commands.member.OnlineCommand;
import ru.ItzDarth.DarthRPG.commands.member.OptionCommand;
import ru.ItzDarth.DarthRPG.commands.member.PartyCommand;
import ru.ItzDarth.DarthRPG.commands.member.PingCommand;
import ru.ItzDarth.DarthRPG.commands.member.PlayTimeCommand;
import ru.ItzDarth.DarthRPG.commands.member.QuestCommand;
import ru.ItzDarth.DarthRPG.commands.member.RegionCommand;
import ru.ItzDarth.DarthRPG.commands.member.RenameCommand;
import ru.ItzDarth.DarthRPG.commands.member.ReplyCommand;
import ru.ItzDarth.DarthRPG.commands.member.ResetQuestsCommand;
import ru.ItzDarth.DarthRPG.commands.member.ResetSPCommand;
import ru.ItzDarth.DarthRPG.commands.member.RewardsCommand;
import ru.ItzDarth.DarthRPG.commands.member.RollCommand;
import ru.ItzDarth.DarthRPG.commands.member.SalvageCommand;
import ru.ItzDarth.DarthRPG.commands.member.ShardCommand;
import ru.ItzDarth.DarthRPG.commands.member.SpawnCommand;
import ru.ItzDarth.DarthRPG.commands.member.Spell1Command;
import ru.ItzDarth.DarthRPG.commands.member.Spell2Command;
import ru.ItzDarth.DarthRPG.commands.member.Spell3Command;
import ru.ItzDarth.DarthRPG.commands.member.Spell4Command;
import ru.ItzDarth.DarthRPG.commands.member.SpellCommand;
import ru.ItzDarth.DarthRPG.commands.member.TopCommand;
import ru.ItzDarth.DarthRPG.commands.member.TradeCommand;
import ru.ItzDarth.DarthRPG.commands.member.TrinketCommand;
import ru.ItzDarth.DarthRPG.commands.member.VoteCommand;
import ru.ItzDarth.DarthRPG.commands.member.WhisperCommand;
import ru.ItzDarth.DarthRPG.commands.member.WorldBossArenaCommand;
import ru.ItzDarth.DarthRPG.commands.mod.SetWarpCommand;
import ru.ItzDarth.DarthRPG.commands.mod.WarpCommand;
import ru.ItzDarth.DarthRPG.commands.owner.AnnounceCommand;
import ru.ItzDarth.DarthRPG.commands.owner.BurnCommand;
import ru.ItzDarth.DarthRPG.commands.owner.CompleteQuestCommand;
import ru.ItzDarth.DarthRPG.commands.owner.CrashCommand;
import ru.ItzDarth.DarthRPG.commands.owner.DBCommand;
import ru.ItzDarth.DarthRPG.commands.owner.DeopCommand;
import ru.ItzDarth.DarthRPG.commands.owner.EditLoreCommand;
import ru.ItzDarth.DarthRPG.commands.owner.EditNameCommand;
import ru.ItzDarth.DarthRPG.commands.owner.FindDupesCommand;
import ru.ItzDarth.DarthRPG.commands.owner.FindItemCommand;
import ru.ItzDarth.DarthRPG.commands.owner.GenerateItemsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.GiveItemCommand;
import ru.ItzDarth.DarthRPG.commands.owner.HealCommand;
import ru.ItzDarth.DarthRPG.commands.owner.KillCommand;
import ru.ItzDarth.DarthRPG.commands.owner.LevelCommand;
import ru.ItzDarth.DarthRPG.commands.owner.LoadWorldCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ManaCommand;
import ru.ItzDarth.DarthRPG.commands.owner.MobSpawnCommand;
import ru.ItzDarth.DarthRPG.commands.owner.MonitorCommand;
import ru.ItzDarth.DarthRPG.commands.owner.MotdCommand;
import ru.ItzDarth.DarthRPG.commands.owner.OpCommand;
import ru.ItzDarth.DarthRPG.commands.owner.PoisonCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReflectCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReflectGetCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadDungeonsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadGenericsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadHayCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadHologramsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadHorsesCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadItemsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadMobsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadQuestsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadRegionsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadShopsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ReloadWarpsCommand;
import ru.ItzDarth.DarthRPG.commands.owner.RerollCommand;
import ru.ItzDarth.DarthRPG.commands.owner.MisakaCommand;
import ru.ItzDarth.DarthRPG.commands.owner.SakiCommand;
import ru.ItzDarth.DarthRPG.commands.owner.SeekItemCommand;
import ru.ItzDarth.DarthRPG.commands.owner.SetBankCommand;
import ru.ItzDarth.DarthRPG.commands.owner.SetSpawnCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ShadowMuteCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ShutdownCommand;
import ru.ItzDarth.DarthRPG.commands.owner.SpawnMobCommand;
import ru.ItzDarth.DarthRPG.commands.owner.TPHideCommand;
import ru.ItzDarth.DarthRPG.commands.owner.ViewBankCommand;

public class CommandManager extends ru.ItzDarth.DarthCore.commands.CommandManager {

    public CommandManager(SakiCore plugin) {
        super(plugin);
    }

    @Override
    public void initialize() {
        super.initialize();
        // Member
        register(Rank.MEMBER, new OnlineCommand("online", "players"));
        register(Rank.MEMBER, new WhisperCommand("w", "whisper", "tell", "pm", "message", "msg"));
        register(Rank.MEMBER, new ReplyCommand("r", "reply"));
        register(Rank.MEMBER, new ClassCommand("class", "classes"));
        register(Rank.MEMBER, new SpellCommand("spell", "spells", "magic", "sp", "spl", "spls"));
        register(Rank.MEMBER, new InfoCommand("info", "information", "details", "detail", "spy"));
        register(Rank.MEMBER, new ClearCommand("clear", "clearinventory"));
        register(Rank.MEMBER, new ResetSPCommand("resetsp"));
        register(Rank.MEMBER, new OptionCommand("option", "options", "opt", "o"));
        register(Rank.MEMBER, new HelpCommand("help", "commands", "command", "?"));
        register(Rank.MEMBER, new Spell1Command("spell1", "1", "s1"));
        register(Rank.MEMBER, new Spell2Command("spell2", "2", "s2"));
        register(Rank.MEMBER, new Spell3Command("spell3", "3", "s3"));
        register(Rank.MEMBER, new Spell4Command("spell4", "4", "s4"));
        register(Rank.MEMBER, new ShardCommand("shard", "shards", "eco", "econ", "economy", "bal", "balance", "gold", "money"));
        register(Rank.MEMBER, new LocationCommand("loc", "location"));
        register(Rank.MEMBER, new RegionCommand("region", "reg"));
        register(Rank.MEMBER, new QuestCommand("quest", "q", "quests"));
        //        register(Rank.MEMBER, new ResetQuestsCommand("resetquests", "resetquest"));
        register(Rank.MEMBER, new GuildCommand("guild", "g", "guilds"));
        register(Rank.MEMBER, new TradeCommand("trade"));
        register(Rank.MEMBER, new PartyCommand("party", "p"));
        register(Rank.MEMBER, new TrinketCommand("trinket", "t"));
        register(Rank.MEMBER, new LookupCommand("lookup"));
        register(Rank.MEMBER, new PlayTimeCommand("playtime", "timeplayed", "playedtime", "played"));
        register(Rank.MEMBER, new RewardsCommand("rewards", "reward", "votepoints", "votepoint", "rewardshop"));
        //        register(Rank.MEMBER, new TeleportAcceptCommand("tpa")); // removed 2.0.1
        register(Rank.MEMBER, new EffectsCommand("e", "effect", "effects", "particle", "particles"));
        register(Rank.MEMBER, new VoteCommand("vote", "votes", "voting"));
        register(Rank.MEMBER, new SpawnCommand("spawn"));
        register(Rank.MEMBER, new SalvageCommand("salvage", "sell"));
        register(Rank.MEMBER, new BankCommand("bank"));
        register(Rank.MEMBER, new RollCommand("roll", "rtd"));
        register(Rank.MEMBER, new HorseCommand("horse", "h"));
        register(Rank.MEMBER, new RenameCommand("rename"));
        register(Rank.MEMBER, new IgnoreCommand("ignore"));
        register(Rank.MEMBER, new PingCommand("ping"));
        register(Rank.MEMBER, new FlightCommand("soaring", "flight", "soar"));
        register(Rank.MEMBER, new TopCommand("top", "leader", "tops", "leaders", "leaderboard", "toplist"));

        register(Rank.MEMBER, new WorldBossArenaCommand("worldboss", "worldbossarena"));

        // Beta

        // Helper

        // Gamemaster
        register(Rank.GAMEMASTER, new NPCCommand("npc"));
        register(Rank.GAMEMASTER, new GMCommand("makegm"));

        // Builder
        register(Rank.BUILDER, new BuilderCommand("builder"));
        register(Rank.BUILDER, new VoxelSniperCommand("vxme"));
        register(Rank.BUILDER, new TerraformCommand("terraform", "tf"));
        register(Rank.BUILDER, new SakiReplaceCommand("sakireplace"));
        register(Rank.BUILDER, new NightVisionCommand("nightvision"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.BUILDER, new GamemodeCommand("gamemode", "gm"));
        register(Rank.BUILDER, new WorldEditCommand("weme"));

        // Mod
        register(SakiCore.TEST_REALM ? Rank.MEMBER : Rank.MOD, new SetWarpCommand("setwarp"));
        register(SakiCore.TEST_REALM ? Rank.MEMBER : Rank.MOD, new WarpCommand("warp"));

        // Admin
        register(Rank.ADMIN, new ClearNearbyCommand("clearnearby"));
        register(Rank.ADMIN, new SwapCommand("swap"));

        // Owner
        register(Rank.OWNER, new GrappleCommand("grapple"));
        register(Rank.OWNER, new DBCommand("db"));
        register(Rank.OWNER, new LoadWorldCommand("loadworld"));
        register(Rank.OWNER, new SetSpawnCommand("setspawn"));
        register(Rank.OWNER, new ReflectCommand("reflect"));
        register(Rank.OWNER, new MotdCommand("motd"));
        register(Rank.OWNER, new ReflectGetCommand("reflectget"));
        register(Rank.OWNER, new MisakaCommand("misaka"));
        register(Rank.OWNER, new ReloadMobsCommand("reloadmobs"));
        register(Rank.OWNER, new AnnounceCommand("announce"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new GiveItemCommand("giveitem", "item", "items"));
        register(Rank.OWNER, new ReloadItemsCommand("reloaditems"));
        register(Rank.OWNER, new OpCommand("op"));
        register(Rank.OWNER, new DeopCommand("deop"));
        register(Rank.OWNER, new ReloadCommand("reload", "rl"));
        register(Rank.OWNER, new KillCommand("kill"));
        register(Rank.OWNER, new MonitorCommand("monitor"));
        register(Rank.OWNER, new ReloadWarpsCommand("reloadwarps"));
        register(Rank.OWNER, new TPHideCommand("tphide"));
        register(Rank.OWNER, new ManaCommand("mana"));
        register(Rank.OWNER, new CrashCommand("crash"));
        register(Rank.OWNER, new BurnCommand("burn"));
        register(Rank.OWNER, new PoisonCommand("poison"));
        register(Rank.OWNER, new ReloadRegionsCommand("reloadregions"));
        register(Rank.OWNER, new ReloadQuestsCommand("reloadquests"));
        register(Rank.OWNER, new ReloadGenericsCommand("reloadgenerics"));
        register(Rank.OWNER, new MobSpawnCommand("mobspawn", "ms"));
        register(Rank.OWNER, new ShadowMuteCommand("shadowmute"));
        register(Rank.OWNER, new SakiCommand("sakibot", "saki"));
        register(Rank.OWNER, new ReloadHologramsCommand("reloadholograms", "reloadholos"));
        register(Rank.OWNER, new ReloadDungeonsCommand("reloaddungeons"));
        register(Rank.OWNER, new ReloadShopsCommand("reloadshops"));
        register(Rank.OWNER, new WalkSpeedCommand("walkspeed"));
        register(Rank.OWNER, new TestPotionsCommand("testpotions", "testpots", "testpotion"));
        register(Rank.OWNER, new TestEquipsCommand("testequips"));
        register(Rank.OWNER, new MobsCommand("mobs"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new GenerateItemsCommand("generateitems", "generate", "generateitem"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new SeekItemCommand("seekitems", "seekitem", "seek"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new SpawnMobCommand("spawnmob"));
        register(Rank.OWNER, new ViewBankCommand("viewbank", "checkbank", "seebank"));
        register(Rank.OWNER, new BlockLocCommand("blockloc"));
        //        register(Rank.OWNER, new MaintenanceCommand("maintenance"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new ResetQuestsCommand("resetquests", "resetquest"));
        register(Rank.OWNER, new ReloadHayCommand("reloadhay", "reloadhaybales"));
        register(Rank.OWNER, new ReloadHorsesCommand("reloadstables", "reloadhorses"));
        register(Rank.OWNER, new ShutdownCommand("shutdown"));
        register(Rank.OWNER, new FindDupesCommand("finddupes"));
        register(Rank.OWNER, new FindItemCommand("finditem"));
        register(Rank.OWNER, new SetBankCommand("setbank"));
        register(Rank.OWNER, new EditLoreCommand("editlore"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new BaseMobCommand("basemob"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new LevelCommand("level"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new HealCommand("heal"));
        register(Rank.OWNER, new RerollCommand("reroll"));
        register(Rank.OWNER, new EditNameCommand("editname"));
        register(SakiRPG.TEST_REALM ? Rank.MEMBER : Rank.OWNER, new CompleteQuestCommand("completequest"));

        // Console - these commands are meant to be run by console, but can be used (carefully) by owner rank
        register(Rank.OWNER, new AdRewardCommand("adreward"));
        register(Rank.OWNER, new BuycraftRewardCommand("buycraftreward"));
    }
}
