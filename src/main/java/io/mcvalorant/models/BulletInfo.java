package io.mcvalorant.models;

public class BulletInfo {

    private final int baseDamage;
    private final float bodyMultiplier;
    private final float legsMultiplier;
    private final float penetrationRate;
    private final int range;
    private final boolean silenced;

    public BulletInfo(int baseDamage, float bodyMultiplier, float legsMultiplier, float penetrationRate, int range, boolean silenced) {
        this.baseDamage = baseDamage;
        this.bodyMultiplier = bodyMultiplier;
        this.legsMultiplier = legsMultiplier;
        this.penetrationRate = penetrationRate;
        this.range = range;
        this.silenced = silenced;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public float getBodyMultiplier() {
        return bodyMultiplier;
    }

    public float getLegsMultiplier() {
        return legsMultiplier;
    }

    public float getPenetrationRate() {
        return penetrationRate;
    }

    public int getRange() {
        return range;
    }

    public boolean isSilenced() {
        return silenced;
    }
}
