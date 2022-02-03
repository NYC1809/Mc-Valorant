package de.nyc.valorant.Weapons;

import de.nyc.valorant.Utils.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Weapons implements Listener {

    private static Main plugin;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getMaterial().equals(Material.WOODEN_AXE)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                Location location_spell = player.getLocation();
                Vector vector = location_spell.toVector();

                BukkitTask bukkittask = new BukkitRunnable() {
                    @Override
                    public void run() {

                        vector.add(vector);

                        //Objects.requireNonNull(player.getLocation().getWorld()).spawnParticle(Particle.COMPOSTER, location_spell, 1);
                        //location_spell = vector.toLocation(player.getWorld());
                    }
                }.runTaskTimer(Main.getPlugin(), 2, 2);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        bukkittask.cancel();
                    }
                }.runTaskLater(Main.getPlugin(), 20 * 5);

            }
        }

    }





}
