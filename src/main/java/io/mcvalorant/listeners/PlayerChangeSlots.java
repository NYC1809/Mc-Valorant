package io.mcvalorant.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerChangeSlots implements Listener {

    @EventHandler
    public void onChangeSlots(PlayerItemHeldEvent event) {
        if (event.getPlayer().getInventory().getItem(event.getNewSlot()) == null) {
            event.setCancelled(true);
        }
    }

}
