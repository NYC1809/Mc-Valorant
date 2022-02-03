package de.nyc.valorant.models;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class GameState {

    public static final int LOBBY_PHASE = 0,
            AGENT_SELECT = 1,
            BUY_PHASE = 2,
            INGAME_PHASE = 3,
            ROUND_OVER = 4,
            OVERTIME_1 = 5,
            OVERTIME_2 = 6,
            ENDGAME_PHASE = 7;

    public abstract void start();
    public abstract void stop();

}
