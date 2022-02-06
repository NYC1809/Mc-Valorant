package io.mcvalorant.models;

public class WeaponInfo {

    private int ammo;
    private int spareAmmo;

    public WeaponInfo(int ammo, int spareAmmo) {
        this.ammo = ammo;
        this.spareAmmo = spareAmmo;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getSpareAmmo() {
        return spareAmmo;
    }

    public void setSpareAmmo(int spareAmmo) {
        this.spareAmmo = spareAmmo;
    }
}
