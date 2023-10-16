package io.mcvalorant.holder;

import io.mcvalorant.enums.Rounds;
import io.mcvalorant.models.RoundStateHandler;

import javax.annotation.Nullable;
import java.util.HashMap;

public class RoundStateHolder {

    private final HashMap<Rounds, RoundStateHandler> handlers = new HashMap<>();
    private Rounds rounds;

    public void registerHandler(Rounds rounds, RoundStateHandler handler) {
        handlers.put(rounds, handler);
    }

    public HashMap<Rounds, RoundStateHandler> getHandlers() {
        return handlers;
    }

    @Nullable
    public RoundStateHandler getCurrentHandler() {
        if(rounds == null) {
            return null;
        }
        return handlers.get(rounds);
    }

    public boolean setRoundState(Rounds rounds) {
        if(!handlers.containsKey(rounds)) {
            return false;
        }
        if(this.rounds != null) {
            handlers.get(this.rounds).stop();
        }
        handlers.get(rounds).start();
        this.rounds = rounds;
        return true;
    }

    public boolean stopCurrentRound() {
        if(rounds == null) {
            return false;
        }
        handlers.get(rounds).stop();
        rounds = null;
        return true;
    }

    @Nullable
    public Rounds getRound() {
        return rounds;
    }


}
