package io.mcvalorant.enums;

public enum Money {
    //Different ways to get money / credits

    INITIAL_MONEY(800),
    FIRST_ROUND_OF_HALF(800),
    KILL(200),
    ROUND_WIN(3000),
    ROUND_LOSS(1900),
    TWO_CONSECUTIVE_ROUND_LOSSES(2400),
    THREE_OR_MORE_ROUND_LOSSES(2900),
    SPIKE_PLANTED(300),
    SPKE_DEFUSED(300),

    MAX_CREDITS(9000),
    OVERTIME_CREDITS(5000);

    private final int moneyValue;

    Money(int moneyValue) {
        this.moneyValue = moneyValue;
    }

    public int getMoneyValue() {
        return moneyValue;
    }
}
