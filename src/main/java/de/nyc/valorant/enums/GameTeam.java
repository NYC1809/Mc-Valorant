package de.nyc.valorant.enums;

import com.sun.tools.javac.util.Pair;
import de.nyc.valorant.models.KeyValuePair;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public enum GameTeam {

    TEAM1("Team 1 | ", ChatColor.RED,
            new KeyValuePair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER),
            new KeyValuePair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    TEAM2("Team 2 | ", ChatColor.BLUE,
            new KeyValuePair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER),
            new KeyValuePair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    SPECTATOR("Spectator | ", ChatColor.GRAY,
            new KeyValuePair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM),
            new KeyValuePair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    ;

    private final String header;
    private final ChatColor color;
    private final KeyValuePair<Team.Option, Team.OptionStatus>[] options;

    @SafeVarargs
    GameTeam(String header, ChatColor color, KeyValuePair<Team.Option, Team.OptionStatus>... options) {
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

    public KeyValuePair<Team.Option, Team.OptionStatus>[] getOptions() {
        return options;
    }
}
