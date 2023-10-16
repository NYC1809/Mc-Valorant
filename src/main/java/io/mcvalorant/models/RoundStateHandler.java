package io.mcvalorant.models;

import io.mcvalorant.MCValorant;

public abstract class RoundStateHandler {

    protected final MCValorant main;

    public RoundStateHandler(MCValorant main) {
        this.main = main;
    }

    public abstract void start();
    public abstract void stop();

}
