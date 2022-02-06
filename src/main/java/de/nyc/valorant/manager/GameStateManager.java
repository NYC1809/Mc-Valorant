package de.nyc.valorant.manager;

import de.nyc.valorant.MCValorant;
import de.nyc.valorant.gamestates.*;
import de.nyc.valorant.models.GameState;

public class GameStateManager {

    private final MCValorant plugin;
    private final GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager(MCValorant plugin) {
        this.plugin = plugin;
        gameStates = new GameState[8];

        gameStates[GameState.LOBBY_PHASE] = new LobbyPhase(plugin);
        gameStates[GameState.AGENT_SELECT] = new AgentSelect(plugin);
        gameStates[GameState.BUY_PHASE] = new BuyPhase(plugin);
        gameStates[GameState.INGAME_PHASE] = new IngamePhase(plugin);
        gameStates[GameState.ROUND_OVER] = new RoundOverPhase(plugin);
        gameStates[GameState.OVERTIME_1] = new Overtime1(plugin);
        gameStates[GameState.OVERTIME_2] = new Overtime2(plugin);
        gameStates[GameState.ENDGAME_PHASE] = new EndgamePhase(plugin);
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

    public String getCurrentGameStateNameActive() {
        String[] list;
        list =  currentGameState.toString().split("\\.");

        String[] list_2;
        list_2 = list[4].split("@");

        return list_2[0];
    }



}
