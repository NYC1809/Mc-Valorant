package io.mcvalorant.listeners;

import io.mcvalorant.GameStateHolder;
import io.mcvalorant.MCValorant;
import io.mcvalorant.controller.ScoreboardTeamsController;
import io.mcvalorant.enums.GameState;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.models.WeaponInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerJoin implements Listener {

    private final MCValorant main;

    public PlayerJoin(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ScoreboardTeamsController tm = main.getScoreboardTeamsController();
        GameStateHolder gameStateHolder = main.getGameStateHolder();

        if (gameStateHolder.getGameState() == GameState.LOBBY_PHASE) {
            if(!tm.getScoreboard().getTeam(GameTeam.TEAM1.name()).getEntries().contains(player.getName()) || !tm.getScoreboard().getTeam(GameTeam.TEAM2.name()).getEntries().contains(player.getName())) {
                if (tm.getTeamSize(GameTeam.TEAM1) >= 5 && tm.getTeamSize(GameTeam.TEAM2) >= 5) {
                    tm.addToTeam(GameTeam.SPECTATOR, player);
                } else {
                    Random random = new Random();
                    switch (random.nextInt(2)) {
                        case 0:
                            if (tm.getTeamSize(GameTeam.TEAM1) >= 5) {
                                tm.addToTeam(GameTeam.TEAM2, player);
                            } else {
                                tm.addToTeam(GameTeam.TEAM1, player);
                            }
                            break;
                        case 1:
                            if (tm.getTeamSize(GameTeam.TEAM2) >= 5) {
                                tm.addToTeam(GameTeam.TEAM1, player);
                            } else {
                                tm.addToTeam(GameTeam.TEAM2, player);
                            }
                            break;
                        default:
                            tm.addToTeam(GameTeam.SPECTATOR, player);
                            break;
                    }
                }
            }
        } else {
            tm.addToTeam(GameTeam.SPECTATOR, player);
        }

        main.getLogger().info("Players in team 1: " + tm.getTeamSize(GameTeam.TEAM1));
        main.getLogger().info("Players in team 2: " + tm.getTeamSize(GameTeam.TEAM2));

        if (!main.getIngamePlayers().containsKey(player.getUniqueId())) {
            main.getIngamePlayers().put(player.getUniqueId(), new IngamePlayer(100, 0));
        }
        IngamePlayer ig = main.getIngamePlayers().get(player.getUniqueId());
        ig.getWeapons().clear();
        for (int i = 0; i < 9; i++) {
            ItemStack weaponItem = player.getInventory().getItem(i);
            if (weaponItem != null) {
                Weapon weapon = Weapon.fromMaterial(weaponItem.getType());
                if (weapon != null) {
                    ig.getWeapons().put(weapon, new WeaponInfo(
                            weapon.getMagazineSize(),
                            weapon.getInitialAmmo() - weapon.getMagazineSize())
                    );
                }
            }
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            event.setResult(PlayerLoginEvent.Result.ALLOWED);
            return;
        }

        if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()) {
            event.setResult(PlayerLoginEvent.Result.KICK_FULL);
            event.kickMessage(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "Valorant in Minecraft" +
                    "\n " + ChatColor.YELLOW + "The Server is full!" +
                    "\n " + ChatColor.YELLOW  + "Please try again later."));
        }

    }


}
