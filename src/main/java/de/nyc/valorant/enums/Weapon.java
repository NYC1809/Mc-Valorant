package de.nyc.valorant.enums;

import de.nyc.valorant.models.BulletInfo;
import org.bukkit.Material;

public enum Weapon {

    VANDAL(Material.DIAMOND_AXE, new BulletInfo(150, .8f, .6f, .8f, 150, false)),
    SPECTRE(Material.GOLDEN_AXE, new BulletInfo(70, .8f, .6f, .2f, 150, true)),
    ;

    private final Material material;
    private final BulletInfo bulletInfo;

    Weapon(Material material, BulletInfo bulletInfo) {
        this.material = material;
        this.bulletInfo = bulletInfo;
    }

    public Material getMaterial() {
        return material;
    }

    public BulletInfo getBulletInfo() {
        return bulletInfo;
    }

}
