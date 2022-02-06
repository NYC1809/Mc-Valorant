package de.nyc.valorant.gamestates;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.models.Config;
import de.nyc.valorant.models.GameState;

public class LobbyPhase extends GameState {

    private final MCValorant main;

    public LobbyPhase(MCValorant main) {
        this.main = main;
    }

    @Override
    public void start() {
        System.out.println("[Valorant] LobbyPhase set!");
        main.getConfiguration().getConfig().set("LobbyPhase.started", true);
        main.getConfiguration().saveConfig();
    }

    @Override
    public void stop() {
        main.getConfiguration().getConfig().set("LobbyPhase.started", false);
        main.getConfiguration().getConfig().saveToString();
    }

    
}
