package com.rc.fortress.game;

import com.badlogic.gdx.math.Vector2;

public abstract class DynamicObject extends GameObject {
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
