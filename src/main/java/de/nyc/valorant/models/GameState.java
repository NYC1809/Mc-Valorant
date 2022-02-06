package de.nyc.valorant.models;

import de.nyc.valorant.MCValorant;

public abstract class GameState {

    protected final MCValorant main;
    public static final int LOBBY_PHASE = 0,
            AGENT_SELECT = 1,
            BUY_PHASE = 2,
            INGAME_PHASE = 3,
            ROUND_OVER = 4,
            OVERTIME_1 = 5,
            OVERTIME_2 = 6,
            ENDGAME_PHASE = 7;

    public GameState(MCValorant main) {
        this.main = main;
    }

    public abstract void start();

    public abstract void stop();

}
