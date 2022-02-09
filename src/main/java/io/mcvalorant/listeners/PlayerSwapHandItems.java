package io.mcvalorant.listeners;

import de.leonheuer.mcguiapi.gui.GUI;
import de.leonheuer.mcguiapi.utils.ItemBuilder;
import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.Weapon;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSwapHandItems implements Listener {

    private final MCValorant main;

    public PlayerSwapHandItems(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        GUI gui = main.getGuiFactory().createGUI(4, "Buy loadout");
        int i = 0;
        for (Weapon w : Weapon.values()) {
            gui.set(i, ItemBuilder.of(w.getMaterial()).name(w.getFriendlyName()).getItem(), e -> {
                e.setCancelled(true);

                ItemStack current = e.getCurrentItem();
                if (current == null) {
                    return;
                }

                Weapon weapon = Weapon.fromMaterial(current.getType());
                if (weapon == null) {
                    return;
                }

                player.getInventory().addItem(current);
                main.getIngamePlayers().get(player.getUniqueId()).getWeapons().put(weapon, weapon.getNewWeaponInfo());
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, .5f, 1.0f);
            });
            i++;
        }
        gui.show(player);
    }

}
