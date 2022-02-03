package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener {

    private final MCValorant main;

    public PlayerInteract(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemOnCursor().getType() != Material.WOODEN_AXE) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location loc = player.getLocation();
            if (loc.getWorld() == null) {
                return;
            }

            Vector direction = loc.getDirection().normalize();
            for (int i = 0; i < 50; i++) {
                loc.getWorld().spawnParticle(Particle.COMPOSTER, loc.add(direction), 1);
            }
        }

    }





}
