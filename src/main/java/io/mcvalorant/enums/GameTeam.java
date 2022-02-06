package io.mcvalorant.enums;

import io.mcvalorant.utils.KeyValuePair;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public enum GameTeam {

    TEAM1("Team 1 | ", NamedTextColor.RED,
            new KeyValuePair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER),
            new KeyValuePair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    TEAM2("Team 2 | ", NamedTextColor.BLUE,
            new KeyValuePair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER),
            new KeyValuePair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    SPECTATOR("Spectator | ", NamedTextColor.GRAY,
            new KeyValuePair<>(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM),
            new KeyValuePair<>(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER)),
    ;

    private final String header;
    private final NamedTextColor color;
    private final KeyValuePair<Team.Option, Team.OptionStatus>[] options;

    @SafeVarargs
    GameTeam(String header, NamedTextColor color, KeyValuePair<Team.Option, Team.OptionStatus>... options) {
        this.header = header;
        this.color = color;
        this.options = options;
    }

    public String getHeader() {
        return header;
    }

    public NamedTextColor getColor() {
        return color;
    }

    public KeyValuePair<Team.Option, Team.OptionStatus>[] getOptions() {
        return options;
    }
}
