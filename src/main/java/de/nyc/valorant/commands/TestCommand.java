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
        Location location = player.getLocation();
        Slime slime = (Slime) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.SLIME);
        slime.setAware(false);
        slime.setInvisible(true);
        slime.setCustomName(ChatColor.RED + "Head");
        slime.setSize(1);
        slime.setInvulnerable(true);
        slime.setCollidable(false);
        slime.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,99999,1,false));

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.addPassenger(slime);
        armorStand.setCollidable(false);

        return false;
    }
}
