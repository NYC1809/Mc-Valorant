package io.mcvalorant;

import io.mcvalorant.enums.GameState;
import io.mcvalorant.models.GameStateHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class GameStateHolder {

    private final HashMap<GameState, GameStateHandler> handlers = new HashMap<>();
    private GameState gameState;

    public void registerHandler(GameState gameState, GameStateHandler handler) {
        handlers.put(gameState, handler);
    }

    public HashMap<GameState, GameStateHandler> getHandlers() {
        return handlers;
    }

    @Nullable
    public GameStateHandler getCurrentHandler() {
        if (gameState == null) {
            return null;
        }
        return handlers.get(gameState);
    }

    public boolean setGameState(GameState gameState) {
        if (!handlers.containsKey(gameState)) {
            return false;
        }
        if (this.gameState != null) {
            handlers.get(this.gameState).stop();
        }
        handlers.get(gameState).start();
        this.gameState = gameState;
        return true;
    }

    public boolean stopCurrentGameState() {
        if (gameState == null) {
            return false;
        }
        handlers.get(gameState).stop();
        gameState = null;
        return true;
    }

    @Nullable
    public GameState getGameState() {
        return gameState;
    }

}
