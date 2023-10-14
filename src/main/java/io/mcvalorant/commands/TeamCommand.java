package io.mcvalorant.commands;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.GameState;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.GameStateHolder;
import io.mcvalorant.controller.ScoreboardTeamsController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamCommand implements CommandExecutor, TabCompleter {

    private final MCValorant main;

    public TeamCommand(MCValorant main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GameStateHolder gm = main.getGameStateManager();
            if (gm.getGameState() == GameState.LOBBY_PHASE) {
                player.sendMessage(">> Gamestate: LobbyPhase");

                switch (args[0].toLowerCase()) {
                    case "changeto" -> {
                        switch (args[1].toLowerCase()) {
                            case "team_1" -> changeToTeam(GameTeam.TEAM1, player);
                            case "team_2" -> changeToTeam(GameTeam.TEAM2, player);
                            case "team_spectator" -> changeToTeam(GameTeam.SPECTATOR, player);
                        }
                    }
                    case "set" -> {
                        if(args.length == 3) {
                            switch (args[1].toLowerCase()) {
                                case "team_1" -> changeToTeam(GameTeam.TEAM1, main.getServer().getPlayer(args[2]));
                                case "team_2" -> changeToTeam(GameTeam.TEAM2, main.getServer().getPlayer(args[2]));
                                case "team_spectator" -> changeToTeam(GameTeam.SPECTATOR, main.getServer().getPlayer(args[2]));
                            }

                        } else {
                            player.sendMessage("Please use /team set <team> <player>");
                        }
                    }
                }


            } else {
                player.sendMessage("Gamephase is not in the \"LobbyPhase\"!");
            }


        }

        return false;
    }

    private void changeToTeam(GameTeam team, Player player) {
        ScoreboardTeamsController tm = main.getTabListManager();
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

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> arguments = new ArrayList<>();
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            arguments.add("changeto");
            arguments.add("set");
            StringUtil.copyPartialMatches(args[0], arguments, completions);
        }
        if (args.length == 2) {
            for (GameTeam gameTeam : GameTeam.values()) {
                arguments.add(gameTeam.name().toLowerCase());
            }
        }
        return null;
    }
}
