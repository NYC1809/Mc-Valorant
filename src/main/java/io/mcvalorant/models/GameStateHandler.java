package io.mcvalorant.models;

import io.mcvalorant.MCValorant;

public abstract class GameStateHandler {

    protected final MCValorant main;

    public GameStateHandler(MCValorant main) {
        this.main = main;
    }

    public abstract void start();
    public abstract void stop();

}
