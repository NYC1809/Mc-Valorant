package io.mcvalorant;

import de.leonheuer.mcguiapi.gui.GUIFactory;
import io.mcvalorant.commands.DebugCommand;
import io.mcvalorant.commands.GameStateCommand;
import io.mcvalorant.commands.TeamCommand;
import io.mcvalorant.controller.ScoreboardTeamsController;
import io.mcvalorant.enums.GameState;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.enums.Rounds;
import io.mcvalorant.gamestates.BuyPhase;
import io.mcvalorant.gamestates.IngamePhase;
import io.mcvalorant.gamestates.RoundOverPhase;
import io.mcvalorant.holder.GameStateHolder;
import io.mcvalorant.holder.RoundStateHolder;
import io.mcvalorant.listeners.PlayerBuyLoadout;
import io.mcvalorant.listeners.PlayerChangeSlots;
import io.mcvalorant.listeners.PlayerInteract;
import io.mcvalorant.listeners.PlayerJoin;
import io.mcvalorant.models.BlockInfo;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.rounds.*;
import io.mcvalorant.utils.Config;
import io.mcvalorant.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MCValorant extends JavaPlugin {

    private final Map<Material, BlockInfo> blockInfoMap = new HashMap<>();
    private final Map<UUID, IngamePlayer> ingamePlayers = new HashMap<>();
    private Config configuration;
    private Config langConfig;
    private GameStateHolder gameStateHolder;
    private RoundStateHolder roundStateHolder;
    private ScoreboardTeamsController scoreboardTeamsController;
    private GUIFactory guiFactory;

    @Override
    public void onEnable() {
        LocalDateTime start = LocalDateTime.now();

        loadResources();

        guiFactory = new GUIFactory(this);

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerInteract(this), this);
        pm.registerEvents(new PlayerChangeSlots(this), this);
        pm.registerEvents(new PlayerBuyLoadout(this), this);
        
        registerCommand("team", new TeamCommand(this));
        registerCommand("gamestate", new GameStateCommand(this));
        registerCommand("debugcommand", new DebugCommand(this));
        registerCommand("buy", new PlayerBuyLoadout(this));

        blockInfoMap.put(Material.STONE, new BlockInfo(1f));
        blockInfoMap.put(Material.OAK_WOOD, new BlockInfo(.5f));
        blockInfoMap.put(Material.AIR, new BlockInfo(0));
        blockInfoMap.put(Material.BARRIER, new BlockInfo(0));

        gameStateHolder = new GameStateHolder();
        gameStateHolder.registerHandler(GameState.BUY_PHASE, new BuyPhase(this));
        gameStateHolder.registerHandler(GameState.INGAME_PHASE, new IngamePhase(this));
        gameStateHolder.registerHandler(GameState.ROUND_OVER, new RoundOverPhase(this));

        roundStateHolder = new RoundStateHolder();
        roundStateHolder.registerHandler(Rounds.LOBBY_ROUND, new LobbyRound(this));
        roundStateHolder.registerHandler(Rounds.FIRST_PISTOL, new FirstPistolRound(this));
        roundStateHolder.registerHandler(Rounds.FIRST_HALF, new FirstHalfRound(this));
        roundStateHolder.registerHandler(Rounds.SECOND_PISTOL, new SecondPistolRound(this));
        roundStateHolder.registerHandler(Rounds.SECOND_HALF, new SecondHalfRound(this));
        roundStateHolder.registerHandler(Rounds.LAST_ROUND, new LastRound(this));
        roundStateHolder.registerHandler(Rounds.FIRST_OVERTIME, new FirstOvertimeRound(this));
        roundStateHolder.registerHandler(Rounds.SECOND_OVERTIME, new SecondOvertimeRound(this));
        roundStateHolder.registerHandler(Rounds.MATCH_OVER, new MatchOverRound(this));

        if (!roundStateHolder.setRoundState(Rounds.LOBBY_ROUND)) {
            getLogger().severe("Lobby round failed to start.");
        }

        scoreboardTeamsController = new ScoreboardTeamsController(this);
        scoreboardTeamsController.clearTeam(GameTeam.TEAM1);
        scoreboardTeamsController.clearTeam(GameTeam.TEAM2);
        scoreboardTeamsController.clearTeam(GameTeam.SPECTATOR);

        getLogger().info("loaded in " + Duration.between(start, LocalDateTime.now()).toMillis() + "ms");

        //getServer().getScheduler().runTaskTimerAsynchronously(this, new ActionBarTask(this), 0, 1);

    }

    private void registerCommand(String command, CommandExecutor executor) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd == null) {
            getLogger().severe("No entry for command " + command + " could be found in the plugin.yml.");
            return;
        }
        cmd.setExecutor(executor);
    }

    private void loadResources() {
        getLogger().info("Loading configurations...");
        try {
            Files.createDirectory(this.getDataFolder().toPath());
        } catch (IOException e) {
            getLogger().info("Directory already exists");
        }
        if (FileUtils.copyResource(this, "config.yml")) {
            configuration = new Config(this, "config.yml");
        }
        if (FileUtils.copyResource(this, "en.yml")) {
            langConfig = new Config(this, "en.yml");
        }
    }

    public Map<Material, BlockInfo> getBlockInfoMap() {
        return blockInfoMap;
    }

    public Map<UUID, IngamePlayer> getIngamePlayers() {
        return ingamePlayers;
    }

    public Config getConfiguration() {
        return configuration;
    }

    public Config getLangConfig() {
        return langConfig;
    }

    public GameStateHolder getGameStateHolder() {
        return gameStateHolder;
    }

    public RoundStateHolder getRoundStateHolder() {
        return roundStateHolder;
    }

    public ScoreboardTeamsController getScoreboardTeamsController() {
        return scoreboardTeamsController;
    }

    public GUIFactory getGuiFactory() {
        return guiFactory;
    }
}
