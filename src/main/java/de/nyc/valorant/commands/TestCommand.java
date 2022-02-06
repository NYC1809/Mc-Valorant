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

import java.util.Objects;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        Location location_head = player.getLocation().add(0,-0.1,0);
        Slime slime_head = (Slime) Objects.requireNonNull(location_head.getWorld()).spawnEntity(location_head, EntityType.SLIME);
        slime_head.setAware(false);
        slime_head.setInvisible(true);
        slime_head.setCustomName(ChatColor.RED + "Head");
        slime_head.setSize(1);
        slime_head.setInvulnerable(true);
        slime_head.setCollidable(false);
        slime_head.setGravity(false);
        slime_head.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,99999,1,false));

        ArmorStand armorStand_head = (ArmorStand) location_head.getWorld().spawnEntity(location_head, EntityType.ARMOR_STAND);
        armorStand_head.setInvisible(true);
        armorStand_head.setInvulnerable(true);
        armorStand_head.addPassenger(slime_head);
        armorStand_head.setCollidable(false);
        armorStand_head.setGravity(false);

        Location location_body_1 = player.getLocation().add(0,-0.5,0);
        Slime slime_body_1 = (Slime) Objects.requireNonNull(location_body_1.getWorld()).spawnEntity(location_body_1, EntityType.SLIME);
        slime_body_1.setAware(false);
        slime_body_1.setInvisible(true);
        slime_body_1.setCustomName(ChatColor.YELLOW + "Body_1");
        slime_body_1.setSize(1);
        slime_body_1.setInvulnerable(true);
        slime_body_1.setCollidable(false);
        slime_body_1.setGravity(false);
        slime_body_1.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 1, false));

        ArmorStand armorStand_body_1 = (ArmorStand) location_body_1.getWorld().spawnEntity(location_body_1, EntityType.ARMOR_STAND);
        armorStand_body_1.setInvisible(true);
        armorStand_body_1.setInvulnerable(true);
        armorStand_body_1.addPassenger(armorStand_body_1);
        armorStand_body_1.setCollidable(false);
        armorStand_body_1.setGravity(false);

        Location location_body_2 = player.getLocation().add(0,-0.75,0);
        Slime slime_body_2 = (Slime) Objects.requireNonNull(location_body_2.getWorld()).spawnEntity(location_body_2, EntityType.SLIME);
        slime_body_2.setAware(false);
        slime_body_2.setInvisible(true);
        slime_body_2.setCustomName(ChatColor.YELLOW + "Body_2");
        slime_body_2.setSize(1);
        slime_body_2.setInvulnerable(true);
        slime_body_2.setCollidable(false);
        slime_body_2.setGravity(false);
        slime_body_2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 1, false));

        ArmorStand armorStand_body_2 = (ArmorStand) location_body_2.getWorld().spawnEntity(location_body_2, EntityType.ARMOR_STAND);
        armorStand_body_2.setInvisible(true);
        armorStand_body_2.setInvulnerable(true);
        armorStand_body_2.addPassenger(armorStand_body_2);
        armorStand_body_2.setCollidable(false);
        armorStand_body_2.setGravity(false);



        return false;
    }
}
