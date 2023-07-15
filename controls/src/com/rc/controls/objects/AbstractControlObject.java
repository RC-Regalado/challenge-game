package com.rc.controls.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractControlObject {
	public Vector2 position;
	public Rectangle dimension;

	public AbstractControlObject() {
		position = new Vector2();
		dimension = new Rectangle();
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
