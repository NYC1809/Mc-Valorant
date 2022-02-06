package io.mcvalorant;

import io.mcvalorant.commands.SlimeCommand;
import io.mcvalorant.enums.GameState;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.gamestates.*;
import io.mcvalorant.listeners.PlayerChangeSlots;
import io.mcvalorant.listeners.PlayerInteract;
import io.mcvalorant.listeners.PlayerJoin;
import io.mcvalorant.manager.GameStateManager;
import io.mcvalorant.manager.TabListManager;
import io.mcvalorant.models.BlockInfo;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.models.WeaponInfo;
import io.mcvalorant.utils.Config;
import io.mcvalorant.utils.FileUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
    private GameStateManager gameStateManager;
    private TabListManager tabListManager;

    @SuppressWarnings("StringConcatenation")
    @Override
    public void onEnable() {
        LocalDateTime start = LocalDateTime.now();
        getLogger().info("Loading configurations...");
        if (FileUtils.copyResource(this, "config.yml")) {
            configuration = new Config(this, "config.yml");
        }
        if (FileUtils.copyResource(this, "en.yml")) {
            langConfig = new Config(this, "en.yml");
        }
        blockInfoMap.put(Material.STONE, new BlockInfo(1f));
        blockInfoMap.put(Material.OAK_WOOD, new BlockInfo(.5f));
        blockInfoMap.put(Material.AIR, new BlockInfo(0));

        getLogger().info("Registering handlers...");
        gameStateManager = new GameStateManager();
        gameStateManager.registerHandler(GameState.LOBBY_PHASE, new LobbyPhase(this));
        gameStateManager.registerHandler(GameState.AGENT_SELECT, new AgentSelect(this));
        gameStateManager.registerHandler(GameState.BUY_PHASE, new BuyPhase(this));
        gameStateManager.registerHandler(GameState.INGAME_PHASE, new IngamePhase(this));
        gameStateManager.registerHandler(GameState.ROUND_OVER, new RoundOverPhase(this));
        gameStateManager.registerHandler(GameState.OVERTIME_1, new Overtime1(this));
        gameStateManager.registerHandler(GameState.OVERTIME_2, new Overtime2(this));
        gameStateManager.registerHandler(GameState.ENDGAME_PHASE, new EndgamePhase(this));

        tabListManager = new TabListManager(this);
        tabListManager.clearTeam(GameTeam.TEAM1);
        tabListManager.clearTeam(GameTeam.TEAM2);
        tabListManager.clearTeam(GameTeam.SPECTATOR);
        getLogger().info("Cleared all Teams");

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerInteract(this), this);
        pm.registerEvents(new PlayerChangeSlots(this), this);

        registerCommand("slime", new SlimeCommand());

        if (!gameStateManager.setGameState(GameState.LOBBY_PHASE)) {
            getLogger().severe("Lobby phase failed to start.");
        }

        getLogger().info("§2MC-Valorant loaded in " + Duration.between(start, LocalDateTime.now()).toMillis() + "ms");

        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (getServer().getOnlinePlayers().size() == 0) {
                return;
            }
            for (Player p : getServer().getOnlinePlayers()) {
                ItemStack weaponItem = p.getInventory().getItemInMainHand();
                Weapon weapon = Weapon.fromMaterial(weaponItem.getType());
                if (weapon == null) {
                    continue;
                }

                IngamePlayer ig = ingamePlayers.get(p.getUniqueId());
                WeaponInfo wi = ig.getWeapons().get(weapon);
                if (wi == null) {
                    continue;
                }

                String actionBar = "";
                // shield
                actionBar = actionBar + "§b\uD83D\uDEE1 §f" + ig.getShield();
                // health
                actionBar = actionBar + "  §c♥";
                if (ig.getHealth() <= 33) {
                    actionBar = actionBar + " §c" + ig.getHealth();
                } else if (ig.getHealth() <= 75) {
                    actionBar = actionBar + " §e" + ig.getHealth();
                } else {
                    actionBar = actionBar + " §f" + ig.getHealth();
                }
                actionBar = actionBar + "                ";
                // ammo
                actionBar = actionBar + "§f\uD83D\uDDE1";
                if (wi.getAmmo() <= Math.round(weapon.getMagazineSize() / 3.0)) {
                    actionBar = actionBar + " §c" + wi.getAmmo();
                } else {
                    actionBar = actionBar + " §f" + wi.getAmmo();
                }
                // spare ammo
                actionBar = actionBar + " §f| " + wi.getSpareAmmo();
                p.sendActionBar(Component.text(actionBar));
            }
        }, 0, 1);
    }

    private void registerCommand(String command, CommandExecutor executor) {
        PluginCommand cmd = this.getCommand(command);
        if (cmd == null) {
            getLogger().severe("No entry for command " + command + " could be found in the plugin.yml.");
            return;
        }
        cmd.setExecutor(executor);
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

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public TabListManager getTabListManager() {
        return tabListManager;
    }

}
