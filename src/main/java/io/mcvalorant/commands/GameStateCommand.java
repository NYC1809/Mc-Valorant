package io.mcvalorant.commands;

import io.mcvalorant.holder.GameStateHolder;
import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameStateCommand implements CommandExecutor {

    private final MCValorant main;

    public GameStateCommand(MCValorant main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.isOp()) {
                GameStateHolder gm = main.getGameStateHolder();
                switch (args[0].toLowerCase()) {
                    case "set" -> {
                        try {
                            GameState gameState = GameState.valueOf(args[1]);
                            if (gm.setGameState(gameState)) {
                                player.sendMessage("Game state successfully changed to " + gameState + ".");
                            } else {
                                player.sendMessage("No handler registered for game state " + gameState + ".");
                            }
                        } catch (IllegalArgumentException e) {
                            player.sendMessage("The game state " + args[1] + " does not exist.");
                        }
                    }
                    case "get" -> {
                        if (gm.getGameState() == null) {
                            player.sendMessage("Active Gamestate is null.");
                        } else {
                            player.sendMessage("Active Gamestate: " + gm.getGameState().getFriendlyName());
                        }
                    }
                    default -> player.sendMessage("Possible arguments: set, get");
                }
            }

        }
        return true;
    }
}
