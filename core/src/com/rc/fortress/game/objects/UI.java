package com.rc.fortress.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class UI extends AbstractGameObject {

	public Rectangle cursor;

	public UI() {
		init();
	}	

	private void init() {
		position.set(10, 10);
		dimension.setSize(100, 100);

		cursor = new Rectangle((position.x + dimension.width) / 2 - 5, (position.y + dimension.height )/ 2 - 5
				, 20, 20);
		Gdx.app.debug("OBJECT", "X: " + cursor.x + "Y: " + cursor.y);
	}

	@Override
	public void update(float deltaTime) {
			super.update(deltaTime);
	}
}
