package de.nyc.valorant.gamestates;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.models.GameState;

public class GameStateManager {

    private final MCValorant plugin;
    private final GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager(MCValorant plugin) {
        this.plugin = plugin;
        gameStates = new GameState[8];

        gameStates[GameState.LOBBY_PHASE] = new LobbyPhase(plugin);
        gameStates[GameState.AGENT_SELECT] = new AgentSelect();
        gameStates[GameState.BUY_PHASE] = new BuyPhase();
        gameStates[GameState.INGAME_PHASE] = new IngamePhase();
        gameStates[GameState.ROUND_OVER] = new RoundOverPhase();
        gameStates[GameState.OVERTIME_1] = new Overtime_1();
        gameStates[GameState.OVERTIME_2] = new Overtime_2();
        gameStates[GameState.ENDGAME_PHASE] = new EndgamePhase();
    }

    public void setCurrentGameState(int gameStateID) {
        if(currentGameState != null) {
            currentGameState.stop();
        }
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public void stopCurrentGameState() {
        if (currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;

        }

    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }



}
