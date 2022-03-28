package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Key extends GameObject {
    public static final float KEY_WIDTH = 0.5f;
    public static final float KEY_HEIGHT = 0.8f;
    public static final int KEY_SCORE = 10;

    public Key(float x, float y) {
        super(x, y, KEY_WIDTH, KEY_HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
