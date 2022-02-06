package io.mcvalorant.listeners;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.models.BlockInfo;
import io.mcvalorant.models.BulletInfo;
import io.mcvalorant.utils.KeyValuePair;
import org.bukkit.Location;
import org.bukkit.Particle;
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
            if (main.getLastShots().containsKey(player)) {
                KeyValuePair<Weapon, LocalDateTime> lastShot = main.getLastShots().get(player);
                if (lastShot.getKey() == weapon) {
                    Duration wait = Duration.ofMillis(Math.round(1000 / weapon.getShotsPerSec()));
                    if (Duration.between(lastShot.getValue(), LocalDateTime.now()).compareTo(wait) <= 0) {
                        return;
                    }
                }
            }

            loc.getWorld().playSound(loc, weapon.getSound(), 1.0f, weapon.getPitch());

            BulletInfo bi = weapon.getBulletInfo();
            boolean hasPenetrated = false;
            float damage = bi.getBaseDamage();
            // TODO modify direction for bullet spray
            Vector direction = loc.getDirection().normalize();

            for (int i = 0; i < weapon.getBulletInfo().getRange(); i++) {
                Block pos = loc.getBlock();
                if (main.getBlockInfoMap().containsKey(pos.getType())) {
                    BlockInfo info = main.getBlockInfoMap().get(pos.getType());
                    // bullet can only penetrate once
                    if (info.getThickness() > 0) {
                        if (hasPenetrated) {
                            break;
                        }
                        if (bi.getPenetrationRate() > info.getThickness()) {
                            damage = damage * (bi.getPenetrationRate() - info.getThickness());
                            hasPenetrated = true;
                        } else {
                            break;
                        }
                    }
                    if (!bi.isSilenced()) {
                        loc.getWorld().spawnParticle(Particle.COMPOSTER, loc.add(direction), 1);
                    }
                } else {
                    break;
                }
            }

            main.getLastShots().put(player, new KeyValuePair<>(weapon, LocalDateTime.now()));

            // TODO check for player hits
        }

    }





}
