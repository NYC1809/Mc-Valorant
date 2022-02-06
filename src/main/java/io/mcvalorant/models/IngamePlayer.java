package io.mcvalorant.models;

import io.mcvalorant.enums.StatusEffect;
import io.mcvalorant.enums.Weapon;

import java.time.LocalDateTime;
import java.util.HashMap;

public class IngamePlayer {

    private final HashMap<StatusEffect, StatusEffectInfo> statusEffects = new HashMap<>();
    private final HashMap<Weapon, WeaponInfo> weapons = new HashMap<>();
    private final HashMap<Weapon, LocalDateTime> lastShots = new HashMap<>();
    private int health;
    private int shield;

    public IngamePlayer(int health, int shield) {
        this.health = health;
        this.shield = shield;
    }

    public HashMap<StatusEffect, StatusEffectInfo> getStatusEffects() {
        return statusEffects;
    }

    public HashMap<Weapon, WeaponInfo> getWeapons() {
        return weapons;
    }

    public HashMap<Weapon, LocalDateTime> getLastShots() {
        return lastShots;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

}
