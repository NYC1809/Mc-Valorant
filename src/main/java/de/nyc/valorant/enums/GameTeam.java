package de.nyc.valorant.enums;

import com.sun.tools.javac.util.Pair;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public enum GameTeam {

    TEAM1("Team 1 | ", ChatColor.RED,
            new Pair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER),
            new Pair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    TEAM2("Team 2 | ", ChatColor.BLUE,
            new Pair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER),
            new Pair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    SPECTATOR("Spectator | ", ChatColor.GRAY,
            new Pair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM),
            new Pair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    ;

    private final String header;
    private final ChatColor color;
    private final Pair<Team.Option, Team.OptionStatus>[] options;

    @SafeVarargs
    GameTeam(String header, ChatColor color, Pair<Team.Option, Team.OptionStatus>... options) {
        this.header = header;
        this.color = color;
        this.options = options;
    }

    public String getHeader() {
        return header;
    }

    public ChatColor getColor() {
        return color;
    }

    public Pair<Team.Option, Team.OptionStatus>[] getOptions() {
        return options;
    }
}
