package de.nyc.valorant.ranks;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.enums.GameTeam;
import de.nyc.valorant.models.KeyValuePair;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TablistManager {

    private final MCValorant main;
    private Scoreboard scoreboard;

    public TablistManager(MCValorant main) {
        this.main = main;
        ScoreboardManager sm = main.getServer().getScoreboardManager();
        if (sm == null) {
            throw new NullPointerException();
        }
        scoreboard = sm.getMainScoreboard();
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


