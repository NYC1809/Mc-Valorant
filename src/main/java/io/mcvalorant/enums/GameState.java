package io.mcvalorant.enums;

import org.apache.commons.lang3.StringUtils;

public enum GameState {

    BUY_PHASE,
    INGAME_PHASE,
    ROUND_OVER;

    public String getFriendlyName() {
        String[] name = this.name().toLowerCase().split("_");
        name[0] = StringUtils.capitalize(name[0]);
        return String.join(" ", name);
    }

}
