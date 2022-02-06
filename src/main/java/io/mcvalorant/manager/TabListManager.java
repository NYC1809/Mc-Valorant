package io.mcvalorant.manager;

import io.mcvalorant.MCValorant;
import io.mcvalorant.enums.GameTeam;
import io.mcvalorant.utils.KeyValuePair;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TabListManager {

    private final MCValorant main;
    private final Scoreboard scoreboard;

    public TabListManager(MCValorant main) {
        this.main = main;
        ScoreboardManager sm = main.getServer().getScoreboardManager();
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
        st.prefix(Component.text(team.getColor() + team.getHeader()));
        st.suffix(Component.text(""));
        st.color(team.getColor());
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


