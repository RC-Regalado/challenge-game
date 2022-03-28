package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Oven extends GameObject {
    public static float OVEN_WIDTH = 1.7f;
    public static float OVEN_HEIGHT = 1.7f;

    public Oven(float x, float y){
        super(x, y, OVEN_WIDTH, OVEN_HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
