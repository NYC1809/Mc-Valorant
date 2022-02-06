package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.enums.Weapon;
import de.nyc.valorant.models.BlockInfo;
import de.nyc.valorant.models.BulletInfo;
import de.nyc.valorant.models.KeyValuePair;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.time.LocalDateTime;

public class PlayerInteract implements Listener {

    private final MCValorant main;

    public PlayerInteract(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack weaponItem = player.getInventory().getItemInMainHand();
        Weapon weapon = null;
        for (Weapon w : Weapon.values()) {
            if (w.getMaterial() == weaponItem.getType()) {
                weapon = w;
                break;
            }
        }
        if (weapon == null) {
            return;
        }

        Location loc = player.getEyeLocation();
        if (loc.getWorld() == null) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (main.lastShot.containsKey(player)) {
                KeyValuePair<Weapon, LocalDateTime> lastShot = main.lastShot.get(player);
                if (lastShot.getKey() == weapon) {
                    Duration wait = Duration.ofMillis(Math.round(1000 / weapon.getShotsPerSec()));
                    if (Duration.between(lastShot.getValue(), LocalDateTime.now()).compareTo(wait) <= 0) {
                        return;
                    }
                }
            }

            BulletInfo bi = weapon.getBulletInfo();
            boolean hasPenetrated = false;
            float damage = bi.baseDamage;
            Vector direction = loc.getDirection().normalize();
            for (int i = 0; i < weapon.getBulletInfo().range; i++) {
                Block pos = loc.getBlock();
                if (main.blockInfoMap.containsKey(pos.getType())) {
                    BlockInfo info = main.blockInfoMap.get(pos.getType());
                    // bullet can only penetrate once
                    if (info.thickness > 0) {
                        if (hasPenetrated) {
                            break;
                        }
                        if (bi.penetrationRate > info.thickness) {
                            damage = damage * (bi.penetrationRate - info.thickness);
                            hasPenetrated = true;
                        } else {
                            break;
                        }
                    }
                    if (!bi.silenced) {
                        loc.getWorld().spawnParticle(Particle.COMPOSTER, loc.add(direction), 1);
                    }
                    loc.getWorld().playSound(loc, Sound.BLOCK_STONE_BREAK, 1.0f, 1.5f);
                } else {
                    break;
                }
            }

            main.lastShot.put(player, new KeyValuePair<>(weapon, LocalDateTime.now()));

            // TODO check for player hits
        }

    }





}
