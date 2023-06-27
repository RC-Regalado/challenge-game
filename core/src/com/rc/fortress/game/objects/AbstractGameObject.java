package com.rc.fortress.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
	public Vector2 position;
	public Vector2 origin;
	public Vector2 scale;
	public Rectangle dimension;
	public float rotation;

	public AbstractGameObject() {
		position = new Vector2();
		dimension = new Rectangle();
		origin = new Vector2();
		scale = new Vector2(1, 1);

		rotation = 0;
	}

	public void update(float deltaTime) {
		dimension.getPosition(position);
	}

	public void setX(float x){
		position.x = x;
	}

	public void setY(float y){
		position.y = y;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

}
