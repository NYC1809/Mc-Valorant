package de.nyc.valorant.enums;

import org.apache.commons.lang.StringUtils;

public enum GameState {

    LOBBY_PHASE,
    AGENT_SELECT,
    BUY_PHASE,
    INGAME_PHASE,
    ROUND_OVER,
    OVERTIME_1,
    OVERTIME_2,
    ENDGAME_PHASE,
    ;

    public String getFriendlyName() {
        String[] name = this.name().toLowerCase().split("_");
        name[0] = StringUtils.capitalize(name[0]);
        return String.join(" ", name);
    }

}
