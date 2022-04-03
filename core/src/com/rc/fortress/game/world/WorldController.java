package com.rc.fortress.game.world;

import com.rc.fortress.game.Muffin;
import com.rc.fortress.game.Platform;

public class WorldController {
    public interface WorldListener {
        void jump();

        void highJump();

        void hit();

        void coin();
    }

    private final LevelController level;
    private final WorldListener listener;

    public float heightSoFar;
    public int score;


    public enum WorldState {
        RUNNING,
        PAUSED,
        NEXT_LEVEL,
        GAME_OVER
    }

    public WorldState state;

    public WorldController(WorldListener listener) {
        level = new LevelController();

        this.heightSoFar = 0;
        this.score = 0;
        this.state = WorldState.RUNNING;

        this.listener = listener;
    }

    private void updateMuffin (float deltaTime, float accelX) {
        if (!level.muffin.is(Muffin.MuffinState.HIT) && level.muffin.position.y <= 0.5f)
            level.muffin.hitPlatform();

        if (level.muffin.is(Muffin.MuffinState.HIT))
            level.muffin.velocity.x = -accelX / 10 * Muffin.MUFFIN_MOVE_VELOCITY;
        level.muffin.update(deltaTime);

        heightSoFar = Math.max(level.muffin.position.y, heightSoFar);
    }

    private void updatePlatforms(float deltaTime) {
        int len = level.platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = level.platforms.get(i);
            platform.update(deltaTime);

            if (platform.pulverized()) {
                level.platforms.remove(platform);
                len = level.platforms.size();
            }
        }
    }

    private void checkPlatformCollisions() {
        if (level.muffin.velocity.y > 0) return;

        int len = level.platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = level.platforms.get(i);
            if (level.muffin.position.y > platform.position.y) {
                if (level.muffin.bounds.overlaps(platform.bounds)) {
                    level.muffin.hitPlatform();
                    listener.jump();
                    if (level.rand.nextFloat() > 0.5f) {
                        platform.pulverize();
                    }
                    break;
                }
            }
        }
    }
}
