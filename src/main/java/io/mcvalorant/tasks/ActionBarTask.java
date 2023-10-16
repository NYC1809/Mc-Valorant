package io.mcvalorant.tasks;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.Rounds;
import io.mcvalorant.enums.Weapon;
import io.mcvalorant.holder.RoundStateHolder;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.models.RoundStateHandler;
import io.mcvalorant.models.WeaponInfo;
import io.mcvalorant.utils.StringFormatUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
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
    private String createActionBarText(@NotNull IngamePlayer ig, WeaponInfo wi, Weapon weapon) {
        // shield
        String actionBar = "§b\uD83D\uDEE1 §f" + ig.getShield() + " ";
        // health
        actionBar = actionBar + " §c♥ " + getColoredHealth(ig.getHealth()) + " ";
        // spacer
        actionBar = actionBar + StringFormatUtils.fixedLengthString(" ", 16);
        // ammo
        actionBar = actionBar + "§f\uD83D\uDDE1";
        long minAmmo = Math.round(weapon.getMagazineSize() / 3.0);
        actionBar = actionBar + getColoredAmmo(wi.getAmmo(), minAmmo, wi.getSpareAmmo());
        // spacer
        actionBar = actionBar + StringFormatUtils.fixedLengthString(" ", 16);
        // money
        actionBar = actionBar + "§f© " + StringFormatUtils.decimalFormatNumber(ig.getMoney());

        return actionBar;
    }

    private String getColoredHealth(int health) {
        if (health <= 33) return "§c" + health;
        if (health <= 75) return "§e" + health;
        return "§f" + health;
    }

    private String getColoredAmmo(int ammo, long minAmmo, int spareAmmo) {
        if (ammo <= minAmmo) return " §c" + ammo + " §f| " + spareAmmo;
        return " §f" + ammo + " §f| " + spareAmmo;
    }

}
