package io.mcvalorant.enums;

import org.apache.commons.lang3.StringUtils;

public enum Rounds {

    LOBBY_ROUND, //Phase to wait for all players
    FIRST_PISTOL, //First round of the game
    FIRST_HALF,
    SECOND_PISTOL, //First round in the second half / more time to buy / swap sites
    SECOND_HALF,
    LAST_ROUND, //Last round in the game before overtime
    FIRST_OVERTIME,
    SECOND_OVERTIME,
    MATCH_OVER; //The Match is over / One team has won or draw

    public String getFriendlyName() {
        String[] name = this.name().toLowerCase().split("_");
        name[0] = StringUtils.capitalize(name[0]);
        return String.join(" ", name);
    }
}
