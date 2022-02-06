package io.mcvalorant.commands;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.manager.GameStateManager;
import io.mcvalorant.manager.TabListManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChangeTeam implements CommandExecutor {

    private final MCValorant main;

    public ChangeTeam(MCValorant main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            TabListManager tm = main.getTabListManager();
            GameStateManager gameStateManager = new GameStateManager();
            assert gameStateManager.getGameState() != null;
            if (gameStateManager.getGameState().getFriendlyName().equals("LobbyPhase")) {
                player.sendMessage(">> Gamestate: LobbyPhase");

                switch (args[0].toLowerCase()) {
                    case "changeto":
                        switch (args[1].toLowerCase()) {
                            case "team_1":
                                changeToTeam(GameTeam.TEAM1, player);
                                break;
                            case "team_2":
                                changeToTeam(GameTeam.TEAM2, player);
                                break;
                            case "team_spectator":
                                changeToTeam(GameTeam.SPECTATOR, player);
                                break;

                        }
                        break;
                    case "set":
                        if(player.isOp()) {
                            if(args.length == 3) {
                                switch (args[1].toLowerCase()) {
                                    case "team_1":
                                        changeToTeam(GameTeam.TEAM1, main.getServer().getPlayer(args[2]));
                                        break;
                                    case "team_2":
                                        changeToTeam(GameTeam.TEAM2, main.getServer().getPlayer(args[2]));
                                        break;
                                    case "team_spectator":
                                        changeToTeam(GameTeam.SPECTATOR, main.getServer().getPlayer(args[2]));
                                        break;
                                }

                            } else {
                                player.sendMessage("Please use /team set <team> <player>");
                            }
                        }
                        break;
                }


            }
        }

        return false;
    }

    private void changeToTeam(GameTeam team, Player player) {
        TabListManager tm = main.getTabListManager();
        if(team.name().equals(GameTeam.TEAM1.name())) {
            if(Objects.requireNonNull(tm.getScoreboard().getTeam(GameTeam.TEAM1.name())).getEntries().contains(player.getName())) {
                player.sendMessage("Du bist schon in Team 1!");
            } else {
                tm.addToTeam(GameTeam.TEAM1, player);
                tm.removeFromTeam(GameTeam.SPECTATOR, player);
                tm.removeFromTeam(GameTeam.TEAM2, player);
            }
        } else if(team.name().equals(GameTeam.TEAM2.name())) {
            if(Objects.requireNonNull(tm.getScoreboard().getTeam(GameTeam.TEAM2.name())).getEntries().contains(player.getName())) {
                player.sendMessage("Du bist schon in Team 2!");
            } else {
                tm.addToTeam(GameTeam.TEAM2, player);
                tm.removeFromTeam(GameTeam.TEAM1, player);
                tm.removeFromTeam(GameTeam.SPECTATOR, player);
            }

        } else if(team.name().equals(GameTeam.SPECTATOR.name())) {
            if(Objects.requireNonNull(tm.getScoreboard().getTeam(GameTeam.SPECTATOR.name())).getEntries().contains(player.getName())) {
                player.sendMessage("Du bist schon in Team Spectator!");
            } else {
                tm.addToTeam(GameTeam.SPECTATOR, player);
                tm.removeFromTeam(GameTeam.TEAM1, player);
                tm.removeFromTeam(GameTeam.TEAM2, player);
            }
        }

    }

}
