package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rc.fortress.utils.Assets;

public class Platform extends DynamicObject {
    private final Assets.AssetPlatform skin;

    public static final float PLATFORM_WIDTH = 2;
    public static final float PLATFORM_HEIGHT = 0.5f;
    public static final int PLATFORM_TYPE_STATIC = 0;
    public static final int PLATFORM_TYPE_MOVING = 1;
    public static final int PLATFORM_STATE_NORMAL = 0;
    public static final int PLATFORM_STATE_PULVERIZING = 1;
    public static final float PLATFORM_PULVERIZE_TIME = 0.2f * 4;
    public static final float PLATFORM_VELOCITY = 2;


    public Platform(int type, float x, float y) {
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);

        skin = Assets.instance.assetPlatform;
//        this.
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x - PLATFORM_WIDTH / 2f;
        bounds.y = position.y - PLATFORM_HEIGHT / 2f;

        if (position.x < PLATFORM_WIDTH / 2f) {
            position.x = PLATFORM_WIDTH / 2f;
            velocity.x = PLATFORM_VELOCITY;
        }
        stateTime += deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(skin.platform, position.x, position.y, bounds.width, bounds.height);
    }
}
