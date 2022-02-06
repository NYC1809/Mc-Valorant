package io.mcvalorant.listeners;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.models.BlockInfo;
import io.mcvalorant.models.BulletInfo;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.models.WeaponInfo;
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
        Weapon weapon = Weapon.fromMaterial(weaponItem.getType());
        if (weapon == null) {
            return;
        }

        Location loc = player.getEyeLocation();
        if (loc.getWorld() == null) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            IngamePlayer ig = main.getIngamePlayers().get(player.getUniqueId());
            WeaponInfo wi = ig.getWeapons().get(weapon);
            if (wi == null) {
                return;
            }
            LocalDateTime lastShot = ig.getLastShots().get(weapon);
            if (lastShot != null) {
                Duration wait = Duration.ofMillis(Math.round(1000 / weapon.getShotsPerSec()));
                if (Duration.between(lastShot, LocalDateTime.now()).compareTo(wait) <= 0) {
                    return;
                }
            }

            if (ig.getWeapons().get(weapon).getAmmo() == 0) {
                loc.getWorld().playSound(loc, Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.5f);
                return;
            }

            player.playSound(loc, weapon.getSound(), 1.0f, weapon.getPitch());

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

            ig.getLastShots().put(weapon, LocalDateTime.now());
            wi.setAmmo(wi.getAmmo() - 1);

            // TODO check for player hits
        }

    }





}
