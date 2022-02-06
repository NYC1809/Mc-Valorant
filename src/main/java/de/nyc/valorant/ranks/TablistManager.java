package de.nyc.valorant.ranks;

import com.sun.tools.javac.util.Pair;
import de.nyc.valorant.enums.GameTeam;
import de.nyc.valorant.models.KeyValuePair;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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
        for (KeyValuePair<Team.Option, Team.OptionStatus> pair : team.getOptions()) {
            st.setOption(pair.getKey(), pair.getValue());
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public int getTeamSize(GameTeam team) {
        try {
            Team scoreboardTeam = scoreboard.getTeam(team.name());
            if (scoreboardTeam == null) {
                return 0;
            }
            return scoreboardTeam.getEntries().size();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean addToTeam(GameTeam team, Player player) {
        Team scoreboardTeam = scoreboard.getTeam(team.name());
        if (scoreboardTeam == null) {
            return false;
        }
        scoreboardTeam.addEntry(player.toString());
        return true;
    }

}


