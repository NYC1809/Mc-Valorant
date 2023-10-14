package io.mcvalorant.tasks;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.models.WeaponInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ActionBarTask implements Runnable {

    private final MCValorant main;

    public ActionBarTask(MCValorant main) {
        this.main = main;
    }

    @Override
    public void run() {
        if (main.getServer().getOnlinePlayers().isEmpty()) {
            return;
        }
        for (Player p : main.getServer().getOnlinePlayers()) {
            ItemStack weaponItem = p.getInventory().getItemInMainHand();
            Weapon weapon = Weapon.fromMaterial(weaponItem.getType());
            if (weapon == null) {
                continue;
            }

            IngamePlayer ig = main.getIngamePlayers().get(p.getUniqueId());
            WeaponInfo wi = ig.getWeapons().get(weapon);
            if (wi == null) {
                continue;
            }

            String actionBar = createActionBarText(ig, wi, weapon);
            p.sendActionBar(Component.text(actionBar));
        }
    }

    @NotNull
    private static String createActionBarText(@NotNull IngamePlayer ig, WeaponInfo wi, Weapon weapon) {
        String actionBar = "";
        // shield
        actionBar = actionBar + "§b\uD83D\uDEE1 §f" + ig.getShield();
        // health
        actionBar = actionBar + "  §c♥";
        if (ig.getHealth() <= 33) {
            actionBar = actionBar + " §c" + ig.getHealth();
        } else if (ig.getHealth() <= 75) {
            actionBar = actionBar + " §e" + ig.getHealth();
        } else {
            actionBar = actionBar + " §f" + ig.getHealth();
        }
        actionBar = actionBar + "                ";
        // ammo
        actionBar = actionBar + "§f\uD83D\uDDE1";
        if (wi.getAmmo() <= Math.round(weapon.getMagazineSize() / 3.0)) {
            actionBar = actionBar + " §c" + wi.getAmmo();
        } else {
            actionBar = actionBar + " §f" + wi.getAmmo();
        }
        // spare ammo
        actionBar = actionBar + " §f| " + wi.getSpareAmmo();
        return actionBar;
    }

}
