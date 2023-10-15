package io.mcvalorant.enums;

public enum Shield {

    LIGHT_SHIELD(25),
    FULL_SHIELD(50);

    private final int shieldValue;

    Shield(int shieldValue) {
        this.shieldValue = shieldValue;
    }

    public int getShieldValue() {
        return shieldValue;
    }

}
