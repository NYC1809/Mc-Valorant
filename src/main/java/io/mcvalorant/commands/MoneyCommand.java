package io.mcvalorant.commands;

import io.mcvalorant.MCValorant;
import io.mcvalorant.models.IngamePlayer;
import io.mcvalorant.utils.FormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoneyCommand implements CommandExecutor {

    private final MCValorant main;

    public MoneyCommand(MCValorant main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if(!player.isOp()) {
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "set" -> {
                if(args.length != 3) {
                    player.sendMessage("Usage /money <set> [player] [value]");
                    return true;
                }
                if(checkIfPlayerExists(player, args[1])) {
                    Player targetPlayer = Bukkit.getPlayer(args[1]);
                    IngamePlayer ig = main.getIngamePlayers().get(targetPlayer.getUniqueId());
                    String inputValue = args[2];
                    if(!FormatUtils.isNumeric(inputValue)) {
                        player.sendMessage("The input " + inputValue + " is not a number");
                        return true;
                    }
                    ig.setMoney(Integer.parseInt(inputValue));
                    player.sendMessage("Successfully set money for " + targetPlayer + " to " + inputValue + ".");
                }
            }
            case "get" -> {
                if(args.length != 2) {
                    player.sendMessage("Usage /money <get> [player]");
                    return true;
                }
                if(checkIfPlayerExists(player, args[1])) {
                    Player targetPlayer = Bukkit.getPlayer(args[1]);
                    IngamePlayer ig = main.getIngamePlayers().get(targetPlayer.getUniqueId());

                    player.sendMessage("The player " + args[1] + " does have " + ig.getMoney() + " money.");
                }
            }
        }

        return false;
    }

    private Boolean checkIfPlayerExists(Player player, String targetPlayer) {
        if(targetPlayer == null) {
            player.sendMessage("The player " + targetPlayer + "does not exist!");
            return false;
        }
        return true;
    }
}
