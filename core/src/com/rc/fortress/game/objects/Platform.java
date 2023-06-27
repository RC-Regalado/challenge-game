package com.rc.fortress.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Platform extends AbstractGameObject implements Poolable {

	public Platform() {
		init();
	}

	private void init() {
		dimension.setSize(500, 50);
	}

	@Override
	public void update(float deltaTime) {
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			position.x = touchPos.x - 64 / 2;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			position.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			position.x += 200 * Gdx.graphics.getDeltaTime();

		if (position.x < 0)
			position.x = 0;
		if (position.x > 800 - dimension.width)
			position.x = 800 - dimension.width;
	}


	@Override
	public void reset() {
		init();
	}
}
