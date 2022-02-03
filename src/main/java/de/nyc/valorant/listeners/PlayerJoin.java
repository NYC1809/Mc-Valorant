package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
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

    public PlayerJoin(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(new TablistManager().getEntriesTeam_1Size() >= 5 && new TablistManager().getEntriesTeam_2Size() >= 5) {

        }

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if(player.isOp()) {
            event.setResult(PlayerLoginEvent.Result.ALLOWED);
        }

        if(Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()) {
            event.setKickMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Valorant in Minecraft" +
                    "\n " + ChatColor.YELLOW + "The Server is full!" +
                    "\n " + ChatColor.YELLOW  + " Please try again later.");
        }

    }


}
