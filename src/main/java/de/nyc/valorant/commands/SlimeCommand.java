package de.nyc.valorant.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SlimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;

        Location location_head = player.getLocation();
        Slime slime_head = (Slime) Objects.requireNonNull(location_head.getWorld()).spawnEntity(location_head, EntityType.SLIME);
        slime_head.setAware(false);
        slime_head.setInvisible(true);
        slime_head.setCustomName(ChatColor.RED + "Head");
        slime_head.setSize(1);
        slime_head.setInvulnerable(true);
        slime_head.setCollidable(false);
        slime_head.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,99999,1,false));

        ArmorStand armorStand_head = (ArmorStand) location_head.getWorld().spawnEntity(location_head, EntityType.ARMOR_STAND);
        armorStand_head.setInvisible(true);
        armorStand_head.setInvulnerable(true);
        armorStand_head.addPassenger(slime_head);
        armorStand_head.setCollidable(false);

        Location location_body = player.getLocation().add(0,-0.5,0);
        Slime slime_body = (Slime) Objects.requireNonNull(location_body.getWorld()).spawnEntity(location_body, EntityType.SLIME);
        slime_body.setAware(false);
        slime_body.setInvisible(true);
        slime_body.setCustomName(ChatColor.YELLOW + "Body");
        slime_body.setSize(1);
        slime_body.setInvulnerable(true);
        slime_body.setCollidable(false);
        slime_body.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 1, false));

        ArmorStand armorStand_body = (ArmorStand) location_body.getWorld().spawnEntity(location_body, EntityType.ARMOR_STAND);
        armorStand_body.setInvulnerable(true);
        armorStand_body.setInvulnerable(true);
        armorStand_body.addPassenger(slime_body);
        armorStand_body.setCollidable(false);

        return true;
    }
}
