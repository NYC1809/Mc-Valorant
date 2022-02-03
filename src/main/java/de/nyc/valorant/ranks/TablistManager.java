package de.nyc.valorant.ranks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;


public class TablistManager {

    private Scoreboard scoreboard;

    public void setPlayerTeams(Player player) {
        scoreboard = player.getScoreboard();

        Team team_1 = scoreboard.getTeam("team_1");
        if (team_1 == null) {
            team_1 = scoreboard.registerNewTeam("team_1");
        }
        team_1.setPrefix(ChatColor.BLUE + "Team 1 | ");
        team_1.setSuffix("");
        team_1.setColor(ChatColor.BLUE);
        team_1.setAllowFriendlyFire(false);
        team_1.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        team_1.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team team_2 = scoreboard.getTeam("team_2");
        if (team_2 == null) {
            team_2 = scoreboard.registerNewTeam("team_2");
        }
        team_2.setPrefix(ChatColor.RED + "Team 2 | ");
        team_2.setSuffix("");
        team_2.setColor(ChatColor.RED);
        team_2.setAllowFriendlyFire(false);
        team_2.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        team_2.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        Team spectator = scoreboard.getTeam("spectator");
        if (spectator == null) {
            spectator = scoreboard.registerNewTeam("spectator");
        }
        spectator.setPrefix(ChatColor.GRAY + "Spectator | ");
        spectator.setSuffix("");
        spectator.setColor(ChatColor.GRAY);
        spectator.setAllowFriendlyFire(false);
        spectator.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
        spectator.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public int getEntriesTeam_1Size() {
        return scoreboard.getTeam("team_1").getSize();
    }

    public int getEntriesTeam_2Size() {
        return scoreboard.getTeam("team_2").getSize();
    }

    public void addTeam_1(Player player) {
        Objects.requireNonNull(scoreboard.getTeam("team_1")).addEntry(player.toString());
    }

    public void addTeam_2(Player player) {
        Objects.requireNonNull(scoreboard.getTeam("team_2")).addEntry(player.toString());
    }

    public void addSpectator(Player player) {
        Objects.requireNonNull(scoreboard.getTeam("spectator")).addEntry(player.toString());
    }

}

