package io.mcvalorant.commands;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.GameState;
import io.mcvalorant.manager.GameStateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SetGameStateCommand implements CommandExecutor {

    private final MCValorant main;

    public SetGameStateCommand(MCValorant main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GameStateManager gameStateManager = new GameStateManager();
            main.registerGamestateHandler();

            switch (args[0].toLowerCase()) {
                case "set":
                    for(GameState gameState_list : GameState.values()) {
                        if(Objects.equals(gameState_list.toString(), args[1])) {
                            if(gameStateManager.setGameState(gameState_list)) {
                                player.sendMessage("Gamestate: " + gameState_list + " entered.");
                            } else {
                                player.sendMessage("Gamestate: " + gameState_list + " failed.");
                                player.sendMessage("No Handlers are registered.");
                            }
                        }
                    }

                    break;
                case "get":
                    if(gameStateManager.getGameState() == null) {
                        player.sendMessage("Active Gamestate is null.");
                    } else {
                        player.sendMessage("Active Gamestate: " + gameStateManager.getGameState().name());
                    }
                    break;
            }

        }


        return false;
    }
}
