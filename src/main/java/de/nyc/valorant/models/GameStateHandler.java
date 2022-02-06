package de.nyc.valorant.models;

import de.nyc.valorant.MCValorant;

public abstract class GameStateHandler {

    protected final MCValorant main;

    public GameStateHandler(MCValorant main) {
        this.main = main;
    }

    public abstract void start();
    public abstract void stop();

}
