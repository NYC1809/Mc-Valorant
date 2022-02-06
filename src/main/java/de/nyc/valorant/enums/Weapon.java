package de.nyc.valorant.enums;

import de.nyc.valorant.models.BulletInfo;
import org.bukkit.Material;

public enum Weapon {

    CLASSIC(Material.WOODEN_AXE, new BulletInfo(78, .3f,.25f,.3f, 200,false)),
    SHORTY(Material.WOODEN_HOE, new BulletInfo(24,.5f, .45f, .1f, 10, false)),
    FRENZY(Material.WOODEN_PICKAXE, new BulletInfo(78, .3f, .25f, .3f,200, false)),
    GHOST(Material.WOODEN_SHOVEL, new BulletInfo(105, .35f, .25f, .4f, 250, false)),
    SHERIFF(Material.WOODEN_SWORD, new BulletInfo(159, .35f, .3f, .8f,250, false)),
    STINGER(Material.STONE_AXE, new BulletInfo(67, .26f, .22f, .2f, 150, false)),
    SPECTRE(Material.GOLDEN_AXE, new BulletInfo(78, .8f, .6f, .2f, 180, false)),
    BUCKY(Material.STONE_HOE, new BulletInfo(40, .5f, .45f, .1f, 20, false)),
    JUDGE(Material.STONE_PICKAXE, new BulletInfo(34, .5f, .45f, .2f, 40, false)),
    BULLDOG(Material.STONE_SHOVEL, new BulletInfo(115, .32f, .28f, .8f, 250, false)),
    GUARDIAN(Material.STONE_SWORD, new BulletInfo(195, .3f, .22f, .8f, 250, false)),
    PHANTOM(Material.GOLDEN_HOE, new BulletInfo(156, .4f, .37f, .8f, 250, false)),
    VANDAL(Material.DIAMOND_AXE, new BulletInfo(160, .8f, .6f, .8f, 250, false)),
    MARSHAL(Material.GOLDEN_PICKAXE, new BulletInfo(202, .5f, .4f, .8f, 250, false)),
    OPERATOR(Material.GOLDEN_SHOVEL, new BulletInfo(255, .7f, .6f, .8f, 350, false)),
    ARES(Material.DIAMOND_SHOVEL, new BulletInfo(72, .4f, .35f, .6f, 250, false)),
    ODIN(Material.DIAMOND_HOE, new BulletInfo(95, .35f, .3f, .7f, 250, false)),

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
