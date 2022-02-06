package io.mcvalorant.enums;

import io.mcvalorant.models.BulletInfo;
import org.bukkit.Material;
import org.bukkit.Sound;

public enum Weapon {

    CLASSIC(Material.WOODEN_AXE, new BulletInfo(78, .3f,.25f,.3f, 200,false), 6.75f, 10, 40, Sound.BLOCK_GLASS_BREAK, 2f,1.0f), //MAYBE GOOD SOUND?
    SHORTY(Material.WOODEN_HOE, new BulletInfo(24,.5f, .45f, .1f, 10, false), 3.33f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    FRENZY(Material.WOODEN_PICKAXE, new BulletInfo(78, .3f, .25f, .3f,200, false), 10.0f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    GHOST(Material.WOODEN_SHOVEL, new BulletInfo(105, .35f, .25f, .4f, 250, false), 6.75f, 10, 40, Sound.BLOCK_GLASS_BREAK, 1.5f,1.0f),
    SHERIFF(Material.WOODEN_SWORD, new BulletInfo(159, .35f, .3f, .8f,250, false), 4.0f, 10, 40, Sound.ENTITY_FIREWORK_ROCKET_BLAST, .4f,.5f), //GOOD SOUND
    STINGER(Material.STONE_AXE, new BulletInfo(67, .26f, .22f, .2f, 150, false), 16.0f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    SPECTRE(Material.GOLDEN_AXE, new BulletInfo(78, .8f, .6f, .2f, 180, false), 13.33f, 10, 40, Sound.BLOCK_GLASS_BREAK, 1.5f,1.0f),
    BUCKY(Material.STONE_HOE, new BulletInfo(40, .5f, .45f, .1f, 20, false), 1.1f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    JUDGE(Material.STONE_PICKAXE, new BulletInfo(34, .5f, .45f, .2f, 40, false), 3.5f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    BULLDOG(Material.STONE_SHOVEL, new BulletInfo(115, .32f, .28f, .8f, 250, false), 10.0f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    GUARDIAN(Material.STONE_SWORD, new BulletInfo(195, .3f, .22f, .8f, 250, false), 5.25f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f),
    PHANTOM(Material.GOLDEN_HOE, new BulletInfo(156, .4f, .37f, .8f, 250, false), 11.0f, 10, 40, Sound.BLOCK_GLASS_BREAK, 1.5f,1.0f),
    VANDAL(Material.DIAMOND_AXE, new BulletInfo(160, .8f, .6f, .8f, 250, false), 9.75f, 10, 40, Sound.BLOCK_STONE_BREAK, 1.5f,1.0f), //GOOD SOUND
    MARSHAL(Material.GOLDEN_PICKAXE, new BulletInfo(202, .5f, .4f, .8f, 250, false), 1.5f, 10, 40, Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 1.3f,1.0f),
    OPERATOR(Material.GOLDEN_SHOVEL, new BulletInfo(255, .7f, .6f, .8f, 350, false), 0.6f, 10, 40, Sound.BLOCK_FENCE_GATE_CLOSE, .5f,2.0f), //GOOD SOUND
    ARES(Material.DIAMOND_SHOVEL, new BulletInfo(72, .4f, .35f, .6f, 250, false), 13.0f, 10, 40, Sound.BLOCK_STONE_BUTTON_CLICK_ON, .8f,1.0f),
    ODIN(Material.DIAMOND_HOE, new BulletInfo(95, .35f, .3f, .7f, 250, false), 12.0f, 10, 40, Sound.BLOCK_STONE_BUTTON_CLICK_ON, .8f,1.0f),
    ;

    private final Material material;
    private final BulletInfo bulletInfo;
    private final float shotsPerSec;
    private final int magazineSize;
    private final int initialAmmo;
    private final Sound sound;
    private final float pitch;
    private final float volume;

    Weapon(Material material, BulletInfo bulletInfo, float shotsPerSec, int magazineSize, int initialAmmo, Sound sound, float pitch, float volume) {
        this.material = material;
        this.bulletInfo = bulletInfo;
        this.shotsPerSec = shotsPerSec;
        this.magazineSize = magazineSize;
        this.initialAmmo = initialAmmo;
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
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
