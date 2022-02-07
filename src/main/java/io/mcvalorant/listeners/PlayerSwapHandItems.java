package io.mcvalorant.listeners;

import de.leonheuer.mcguiapi.gui.GUI;
import de.leonheuer.mcguiapi.utils.ItemBuilder;
import io.mcvalorant.MCValorant;
import org.bukkit.Material;
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
        gui.set(1, 5, new ItemBuilder(Material.WOODEN_SWORD, 1).name("Sherrif").getResult(), (e) -> {
            ItemStack current = e.getCurrentItem();
            if (current != null) {
                player.getInventory().addItem(current);
            }
        });
        gui.show(player);
    }

}
