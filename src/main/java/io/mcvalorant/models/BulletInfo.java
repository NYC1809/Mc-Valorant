package io.mcvalorant.models;

public class BulletInfo {

    public final int baseDamage;
    public final float bodyMultiplier;
    public final float legsMultiplier;
    public final float penetrationRate;
    public final int range;
    public final boolean silenced;

    public BulletInfo(int baseDamage, float bodyMultiplier, float legsMultiplier, float penetrationRate, int range, boolean silenced) {
        this.baseDamage = baseDamage;
        this.bodyMultiplier = bodyMultiplier;
        this.legsMultiplier = legsMultiplier;
        this.penetrationRate = penetrationRate;
        this.range = range;
        this.silenced = silenced;
    }

}
