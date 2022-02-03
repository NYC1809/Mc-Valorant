package de.nyc.valorant.ranks;

import com.sun.tools.javac.util.Pair;
import de.nyc.valorant.enums.GameTeam;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class TablistManager {

    private Scoreboard scoreboard;

    public void setPlayerTeams(Player player) {
        scoreboard = player.getScoreboard();
        registerTeam(GameTeam.TEAM1);
        registerTeam(GameTeam.TEAM2);
        registerTeam(GameTeam.SPECTATOR);
    }

    private void registerTeam(GameTeam team) {
        Team st = scoreboard.getTeam(team.name());
        if (st == null) {
            st = scoreboard.registerNewTeam(team.name());
        }
        st.setPrefix(team.getColor() + team.getHeader());
        st.setSuffix("");
        st.setColor(team.getColor());
        st.setAllowFriendlyFire(false);
        for (Pair<Team.Option, Team.OptionStatus> pair : team.getOptions()) {
            st.setOption(pair.fst, pair.snd);
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public int getTeamSize(GameTeam team) {
        Team scoreboardTeam = scoreboard.getTeam(team.name());
        if (scoreboardTeam == null) {
            return 0;
        }
        return scoreboardTeam.getEntries().size();
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


