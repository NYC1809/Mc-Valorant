package io.mcvalorant.listeners;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.models.WeaponInfo;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerChangeSlots implements Listener {

    private final MCValorant main;

    public PlayerChangeSlots(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onChangeSlots(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Inventory inv = player.getInventory();
        if (player.getInventory().getItem(event.getNewSlot()) != null) {
            return;
        }

        event.setCancelled(true);

        if (event.getNewSlot() == 8) {
            // weapon reload
            ItemStack previous = inv.getItem(event.getPreviousSlot());
            if (previous == null) {
                return;
            }

            Weapon weapon = Weapon.fromMaterial(previous.getType());
            if (weapon == null) {
                return;
            }

            IngamePlayer ig = main.getIngamePlayers().get(player.getUniqueId());
            WeaponInfo wi = ig.getWeapons().get(weapon);
            if (wi == null || wi.getAmmo() == weapon.getMagazineSize() || wi.getSpareAmmo() == 0) {
                return;
            }

            int reloadAmmo = Math.min(wi.getSpareAmmo(), weapon.getMagazineSize() - wi.getAmmo());
            wi.setAmmo(wi.getAmmo() + reloadAmmo);
            wi.setSpareAmmo(wi.getSpareAmmo() - reloadAmmo);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, .5f);
        }
    }

}
