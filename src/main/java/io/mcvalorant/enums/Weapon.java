package io.mcvalorant.enums;

import io.mcvalorant.models.BulletInfo;
import io.mcvalorant.models.WeaponInfo;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;

public enum Weapon {

    CLASSIC(Material.WOODEN_AXE, new BulletInfo(78, .3f,.25f,.3f, 200,false), 6.75f, 12, 48, Sound.BLOCK_GLASS_BREAK, 2f,1.0f, 0), //MAYBE GOOD SOUND?
    SHORTY(Material.WOODEN_HOE, new BulletInfo(24,.5f, .45f, .1f, 10, false), 3.33f, 2, 12, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 300),
    FRENZY(Material.WOODEN_PICKAXE, new BulletInfo(78, .3f, .25f, .3f,200, false), 10.0f, 13, 52, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 450),
    GHOST(Material.WOODEN_SHOVEL, new BulletInfo(105, .35f, .25f, .4f, 250, false), 6.75f, 15, 60, Sound.BLOCK_GLASS_BREAK, 1.5f,1.0f, 500),
    SHERIFF(Material.WOODEN_SWORD, new BulletInfo(159, .35f, .3f, .8f,250, false), 4.0f, 6, 30, Sound.ENTITY_FIREWORK_ROCKET_BLAST, .4f,.5f, 800), //GOOD SOUND
    STINGER(Material.STONE_AXE, new BulletInfo(67, .26f, .22f, .2f, 150, false), 16.0f, 20, 80, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 1100),
    SPECTRE(Material.GOLDEN_AXE, new BulletInfo(78, .8f, .6f, .2f, 180, false), 13.33f, 30, 120, Sound.BLOCK_GLASS_BREAK, 1.5f,1.0f,1600),
    BUCKY(Material.STONE_HOE, new BulletInfo(40, .5f, .45f, .1f, 20, false), 1.1f, 5, 15, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f,850),
    JUDGE(Material.STONE_PICKAXE, new BulletInfo(34, .5f, .45f, .2f, 40, false), 3.5f, 7, 28, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 1850),
    BULLDOG(Material.STONE_SHOVEL, new BulletInfo(115, .32f, .28f, .8f, 250, false), 10.0f, 24, 96, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 2050),
    GUARDIAN(Material.STONE_SWORD, new BulletInfo(195, .3f, .22f, .8f, 250, false), 5.25f, 12, 48, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 2250),
    PHANTOM(Material.GOLDEN_HOE, new BulletInfo(156, .4f, .37f, .8f, 250, false), 11.0f, 30, 120, Sound.BLOCK_GLASS_BREAK, 1.5f,1.0f, 2900),
    VANDAL(Material.DIAMOND_AXE, new BulletInfo(160, .8f, .6f, .8f, 250, false), 9.75f, 25, 100, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f, 2900), //GOOD SOUND
    MARSHAL(Material.GOLDEN_PICKAXE, new BulletInfo(202, .5f, .4f, .8f, 250, false), 1.5f, 5, 20, Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 1.3f,1.0f, 950),
    OPERATOR(Material.GOLDEN_SHOVEL, new BulletInfo(255, .7f, .6f, .8f, 350, false), 0.6f, 5, 15, Sound.BLOCK_FENCE_GATE_CLOSE, .5f,2.0f,4700), //GOOD SOUND
    ARES(Material.DIAMOND_SHOVEL, new BulletInfo(72, .4f, .35f, .6f, 250, false), 13.0f, 50, 150, Sound.BLOCK_STONE_BUTTON_CLICK_ON, .8f,1.0f, 1600),
    ODIN(Material.DIAMOND_HOE, new BulletInfo(95, .35f, .3f, .7f, 250, false), 12.0f, 100, 300, Sound.BLOCK_STONE_BUTTON_CLICK_ON, .8f,1.0f, 3200),
    ;

    private final Material material;
    private final BulletInfo bulletInfo;
    private final float shotsPerSec;
    private final int magazineSize;
    private final int initialAmmo;
    private final Sound sound;
    private final float pitch;
    private final float volume;
    private final int price;

    Weapon(Material material, BulletInfo bulletInfo, float shotsPerSec, int magazineSize, int initialAmmo, Sound sound, float pitch, float volume, int price) {
        this.material = material;
        this.bulletInfo = bulletInfo;
        this.shotsPerSec = shotsPerSec;
        this.magazineSize = magazineSize;
        this.initialAmmo = initialAmmo;
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public BulletInfo getBulletInfo() {
        return bulletInfo;
    }

    public float getShotsPerSec() {
        return shotsPerSec;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public int getInitialAmmo() {
        return initialAmmo;
    }

    public Sound getSound() {
        return sound;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    public String getFriendlyName() {
        String[] name = this.name().toLowerCase().split("_");
        name[0] = StringUtils.capitalize(name[0]);
        return String.join(" ", name);
    }

    public WeaponInfo getNewWeaponInfo() {
        return new WeaponInfo(magazineSize, initialAmmo - magazineSize);
    }

    public static Weapon fromMaterial(Material material) {
        Weapon weapon = null;
        for (Weapon w : Weapon.values()) {
            if (w.getMaterial() == material) {
                weapon = w;
                break;
            }
        }
        return weapon;
    }
}
