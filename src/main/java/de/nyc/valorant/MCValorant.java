package de.nyc.valorant;

import de.nyc.valorant.commands.TestCommand;
import de.nyc.valorant.models.BlockInfo;
import de.nyc.valorant.models.GameState;
import de.nyc.valorant.gamestates.GameStateManager;
import de.nyc.valorant.listeners.PlayerJoin;
import de.nyc.valorant.ranks.TablistManager;
import de.nyc.valorant.models.Config;
import de.nyc.valorant.listeners.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class MCValorant extends JavaPlugin {

    private Config configuration;
    private GameStateManager gameStateManager;
    private TablistManager tablistManager;
    public final Map<Material, BlockInfo> blockInfoMap = new HashMap<>();

    @Override
    public void onEnable() {
        configuration = new Config(this, "config.yml");
        tablistManager = new TablistManager();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerInteract(this), this);

        registerCommand("slime", new TestCommand());

        gameStateManager = new GameStateManager(this);
        gameStateManager.setCurrentGameState(GameState.LOBBY_PHASE);

        blockInfoMap.put(Material.STONE, new BlockInfo(1f));
        blockInfoMap.put(Material.OAK_WOOD, new BlockInfo(.5f));
        blockInfoMap.put(Material.AIR, new BlockInfo(0));
    }

    public Config getConfiguration() {
        return configuration;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public TablistManager getTablistManager() {
        return tablistManager;
    }

    private void registerCommand(String command, CommandExecutor executor) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd == null) {
            getLogger().severe("No entry for command " + command + " could be found in the plugin.yml.");
            return;
        }
        cmd.setExecutor(executor);
    }

}
