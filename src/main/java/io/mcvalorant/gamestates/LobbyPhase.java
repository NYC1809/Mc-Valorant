package io.mcvalorant.gamestates;

import io.mcvalorant.MCValorant;
import io.mcvalorant.models.GameStateHandler;

public class LobbyPhase extends GameStateHandler {

    public LobbyPhase(MCValorant main) {
        super(main);
    }

    @Override
    public void start() {
        main.getLogger().info("[Debug] Lobby phase set.");
        main.getConfiguration().getConfig().set("LobbyPhase.started", true);
        main.getConfiguration().saveConfig();
    }

    @Override
    public void stop() {
        main.getConfiguration().getConfig().set("LobbyPhase.started", false);
        main.getConfiguration().getConfig().saveToString();
    }

    
}
