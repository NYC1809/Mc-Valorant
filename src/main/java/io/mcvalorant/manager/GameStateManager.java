package io.mcvalorant.manager;

import io.mcvalorant.enums.GameState;
import io.mcvalorant.models.GameStateHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Manager which allows registering handlers for each game state.
 */
public class GameStateManager {

    private final HashMap<GameState, GameStateHandler> handlers = new HashMap<>();
    private GameState gameState;

    /**
     * Registers a new handler instance for the given game state.
     * @param gameState The game state
     * @param handler The handler instance
     */
    public void registerHandler(GameState gameState, GameStateHandler handler) {
        handlers.put(gameState, handler);
    }

    /**
     * Gets a map of all registered handlers.
     * @return The map of handlers
     */
    public HashMap<GameState, GameStateHandler> getHandlers() {
        return handlers;
    }

    /**
     * Gets the handler of the current game state.
     * @return Null if no game state is active, null if handler is not registered, the handler otherwise
     */
    @Nullable
    public GameStateHandler getCurrentHandler() {
        if (gameState == null) {
            return null;
        }
        return handlers.getOrDefault(gameState, null);
    }

    /**
     * Sets the current game state and triggers its handler.
     * @param gameState The game state to set
     * @return false if no handler is registered for the given game state, true if the handler has been triggered
     */
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

    /**
     * Stops the current game state.
     * @return false if there is no current game state, true if the handler has been stopped
     */
    public boolean stopCurrentGameState() {
        if (gameState == null) {
            return false;
        }
        handlers.get(gameState).stop();
        gameState = null;
        return true;
    }

    /**
     * Gets the current game state
     * @return null if there is no current game state, the game state otherwise
     */
    @Nullable
    public GameState getGameState() {
        return gameState;
    }

}
