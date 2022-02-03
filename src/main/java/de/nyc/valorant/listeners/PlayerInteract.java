package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener {

    private final MCValorant main;

    public PlayerInteract(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getMaterial().equals(Material.WOODEN_AXE)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Location location_spell = player.getLocation();
                Vector vector = location_spell.toVector();
                BukkitScheduler scheduler = main.getServer().getScheduler();

                BukkitTask task = scheduler.runTaskTimer(main, () -> {
                    vector.add(vector);

                    //Objects.requireNonNull(player.getLocation().getWorld()).spawnParticle(Particle.COMPOSTER, location_spell, 1);
                    //location_spell = vector.toLocation(player.getWorld());
                }, 2L, 2L);

                scheduler.runTaskLater(main, task::cancel, 20 * 5L);
            }
        }

    }





}
