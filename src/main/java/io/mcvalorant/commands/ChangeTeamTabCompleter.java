package io.mcvalorant.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangeTeamTabCompleter implements TabCompleter {

    private static final String[] ARGUMENTE = {"changeto", "set", "team_1", "team_2", "team_spectator"};

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completitions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(ARGUMENTE), completitions);
        Collections.sort(completitions);
        return completitions;
    }
}
