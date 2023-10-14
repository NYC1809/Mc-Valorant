package io.mcvalorant.commands;

import io.mcvalorant.MCValorant;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class DebugCommand implements CommandExecutor {

    private final MCValorant main;
    private final ArrayList<String> playerArrayList = new ArrayList<>();

    public DebugCommand(MCValorant main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(!playerArrayList.contains(player.getName())) {
                playerArrayList.add(player.getName());

                getServer().getScheduler().runTaskTimerAsynchronously(main, () -> {
                    Location location_head = player.getLocation();
                    location_head.getWorld().spawnParticle(Particle.COMPOSTER, location_head.add(0.3, 1.8, 0.3), 1);
                    location_head.getWorld().spawnParticle(Particle.COMPOSTER, location_head.add(-0.3, 1.8, -0.3), 1);
                    location_head.getWorld().spawnParticle(Particle.COMPOSTER, location_head.add(0.3, 1.8, -0.3), 1);
                    location_head.getWorld().spawnParticle(Particle.COMPOSTER, location_head.add(-0.3, 1.8, 0.3), 1);
                }, 0, 5);

            } else {
                playerArrayList.remove(player.getName());
            }
        }
        return false;
    }
}
