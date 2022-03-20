package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
    public final Vector2 position;
    public final Rectangle bounds;

    protected float delta;

    public AbstractGameObject (float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
        this.delta = 0;
    }

    public void update(float deltaTime){
        this.delta += deltaTime;
    }

    public abstract void render(SpriteBatch batch);

    public void setPosition(float x, float y){
        position.set(x, y);
    }
}
