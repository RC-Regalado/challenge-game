package com.rc.fortress.game.world;

import com.badlogic.gdx.math.Vector2;
import com.rc.fortress.game.Key;
import com.rc.fortress.game.Muffin;
import com.rc.fortress.game.Oven;
import com.rc.fortress.game.Platform;
import com.rc.fortress.game.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelController {

    public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 15 * 20;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;

    public static final Vector2 gravity = new Vector2(0, -12);

    private float weaponProbability;
    private int platformCount;
    private int dynamicPlatforms;

    public final Random rand;
    public float heightSoFar;
    public int score;
    public int state;

    public final Muffin muffin;
    public final List<Platform> platforms;
    public final List<Weapon> weapons;
    public final List<Key> keys;
    public Oven oven;

    public LevelController(){
        this.muffin = new Muffin(5, 1);
        this.platforms = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.keys = new ArrayList<>();

        rand = new Random();
        generateLevel();

        this.heightSoFar = 0;
        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
    }

    private void generateLevel () {
        float y = Platform.PLATFORM_HEIGHT / 2f;
        float maxJumpHeight = Muffin.MUFFIN_JUMP_VELOCITY * Muffin.MUFFIN_JUMP_VELOCITY / (2 * -gravity.y);
        while (y < WORLD_HEIGHT - WORLD_WIDTH / 2) {
            int type = rand.nextFloat() > 0.8f ? Platform.PLATFORM_TYPE_MOVING : Platform.PLATFORM_TYPE_STATIC;
            float x = rand.nextFloat() * (WORLD_WIDTH - Platform.PLATFORM_WIDTH) + Platform.PLATFORM_WIDTH / 2;

            Platform platform = new Platform(type, x, y);
            platforms.add(platform);

            if (rand.nextFloat() > 0.9f && type != Platform.PLATFORM_TYPE_MOVING) {
                Weapon weapon = Weapon.generateWeapon(rand.nextFloat(), platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT / 2
                        + Weapon.WEAPON_HEIGHT / 2);

                weapons.add(weapon);
            }

            if (rand.nextFloat() > 0.6f) {
                Key key = new Key(platform.position.x + rand.nextFloat(), platform.position.y + Key.KEY_HEIGHT
                        + rand.nextFloat() * 3);
                keys.add(key);
            }

            y += (maxJumpHeight - 0.5f);
            y -= rand.nextFloat() * (maxJumpHeight / 3);
        }

        oven = new Oven(WORLD_WIDTH / 2, y);
    }
}
