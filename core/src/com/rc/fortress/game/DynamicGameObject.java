package com.rc.fortress.game;

import com.badlogic.gdx.math.Vector2;

public abstract class DynamicGameObject extends AbstractGameObject{
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject (float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
