package io.mcvalorant.listeners;

import de.leonheuer.mcguiapi.gui.GUI;
import de.leonheuer.mcguiapi.utils.ItemBuilder;
import io.mcvalorant.MCValorant;
import io.mcvalorant.controller.ScoreboardTeamsController;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.models.IngamePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerBuyLoadout implements Listener, CommandExecutor {

    private final MCValorant main;

    public PlayerBuyLoadout(MCValorant main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        openBuyInventory(player);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        ScoreboardTeamsController tm = main.getScoreboardTeamsController();
        if(!tm.getScoreboard().getTeam(GameTeam.SPECTATOR.name()).getEntries().contains(player.getName())) {
            openBuyInventory(player);
        }
        return false;
    }

    private void openBuyInventory(Player player) {
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
