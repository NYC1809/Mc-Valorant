package de.nyc.valorant.listeners;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.enums.Weapon;
import de.nyc.valorant.models.BlockInfo;
import de.nyc.valorant.models.BulletInfo;
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

        BulletInfo bi = weapon.getBulletInfo();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location loc = player.getEyeLocation();
            if (loc.getWorld() == null) {
                return;
            }

            boolean hasPenetrated = false;
            float damage = bi.baseDamage;
            Vector direction = loc.getDirection().normalize();
            for (int i = 0; i < weapon.getBulletInfo().range; i++) {
                loc.add(direction);
                Block pos = loc.getBlock();
                if (main.blockInfoMap.containsKey(pos.getType())) {
                    BlockInfo info = main.blockInfoMap.get(pos.getType());
                    // bullet can only penetrate once
                    if (info.thickness > 0 && hasPenetrated) {
                        break;
                    }
                    if (bi.penetrationRate > info.thickness) {
                        damage = damage * (bi.penetrationRate - info.thickness);
                        hasPenetrated = true;
                    }
                    if (!bi.silenced) {
                        loc.getWorld().spawnParticle(Particle.COMPOSTER, loc.add(direction), 1);
                    }
                }
            }

            // TODO check for player hits
        }

    }





}
