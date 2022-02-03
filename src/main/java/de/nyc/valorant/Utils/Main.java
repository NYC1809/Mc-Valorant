package de.nyc.valorant.Utils;

import de.nyc.valorant.Commands.TestCmd;
import de.nyc.valorant.Gamestates.GameState;
import de.nyc.valorant.Gamestates.GameStateManager;
import de.nyc.valorant.Listener.LoginManager;
import de.nyc.valorant.Ranks.TablistManager;
import de.nyc.valorant.Weapons.Weapons;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        System.out.println("[Valorant] Loading...");
        registerListeners();
        registerCommands();

        new Config();

        new TablistManager();
        plugin = this;

        GameStateManager gameStateManager = new GameStateManager(plugin);
        gameStateManager.setGameStates(GameState.LOBBY_PHASE);

        System.out.println("[Valorant] Loaded!");
    }

    @Override
    public void onDisable() {
        System.out.println("[Valorant] Plugin disabled!");
    }


    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new LoginManager(), this);
        pluginManager.registerEvents(new Weapons(), this);

    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("slime")).setExecutor(new TestCmd());

    }

    public static Main getPlugin() {
        return plugin;
    }

}
