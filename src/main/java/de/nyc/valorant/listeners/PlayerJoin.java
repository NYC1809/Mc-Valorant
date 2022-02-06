package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.enums.GameTeam;
import de.nyc.valorant.gamestates.GameStateManager;
import de.nyc.valorant.ranks.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Random;

public class PlayerJoin implements Listener {

    private final MCValorant main;
    private GameStateManager gameStateManager;

    public PlayerJoin(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        TablistManager tm = main.getTablistManager();
        gameStateManager = main.getGameStateManager();

        if (gameStateManager.getCurrentGameStateNameActive().equals("LobbyPhase")) {
            if (tm.getTeamSize(GameTeam.TEAM1) >= 5 && tm.getTeamSize(GameTeam.TEAM2) >= 5) {
                tm.addToTeam(GameTeam.SPECTATOR, player);
            } else {
                Random random = new Random();
                switch (random.nextInt(2)+1) {
                    case 1:
                        if(tm.getTeamSize(GameTeam.TEAM1) >= 5) {
                            tm.addToTeam(GameTeam.TEAM2, player);
                        } else {
                            tm.addToTeam(GameTeam.TEAM1, player);
                        }
                        break;
                    case 2:
                        if(tm.getTeamSize(GameTeam.TEAM2) >= 5) {
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
        } else {
            tm.addToTeam(GameTeam.SPECTATOR, player);
        }

        player.sendMessage("Team_1: " + tm.getTeamSize(GameTeam.TEAM1));
        player.sendMessage("Team_2: " + tm.getTeamSize(GameTeam.TEAM2));



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
            event.setKickMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Valorant in Minecraft" +
                    "\n " + ChatColor.YELLOW + "The Server is full!" +
                    "\n " + ChatColor.YELLOW  + "Please try again later.");
        }

    }


}
