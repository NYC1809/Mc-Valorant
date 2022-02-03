package de.nyc.valorant.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class LoginManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        /*
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Slime slime = (Slime) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.SLIME);
        slime.setAware(false);
        slime.setInvisible(true);
        slime.setCustomName(ChatColor.RED + "Head");
        slime.setSize(1);
        slime.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,99999,1,false));
        */





    }


}
