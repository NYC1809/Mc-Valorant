package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.enums.GameTeam;
import de.nyc.valorant.gamestates.GameStateManager;
import de.nyc.valorant.models.GameState;
import de.nyc.valorant.ranks.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoin implements Listener {

    private final MCValorant main;
    private GameStateManager gameStateManager;

    public PlayerJoin(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        /*
        TablistManager tm = main.getTablistManager();
        if (tm.getTeamSize(GameTeam.TEAM1) >= 5 && tm.getTeamSize(GameTeam.TEAM2) >= 5) {
        }
         */

        gameStateManager = main.getGameStateManager();



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
