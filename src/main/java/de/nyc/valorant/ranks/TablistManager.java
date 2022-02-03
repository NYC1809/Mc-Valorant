package de.nyc.valorant.ranks;

import com.sun.tools.javac.util.Pair;
import de.nyc.valorant.enums.GameTeam;
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

    public boolean addToTeam(GameTeam team, Player player) {
        Team scoreboardTeam = scoreboard.getTeam(team.name());
        if (scoreboardTeam == null) {
            return false;
        }
        scoreboardTeam.addEntry(player.toString());
        return true;
    }

}


