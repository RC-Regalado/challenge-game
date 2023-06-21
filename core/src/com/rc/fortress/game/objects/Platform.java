package com.rc.fortress.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.rc.fortress.utils.Assets;

public class Platform extends AbstractGameObject {
	private TextureRegion platform;

	public Platform() {
		init();
	}

	private void init() {
		dimension.set(500, 50);
		platform = Assets.assets.platform.platform;
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
		if (position.x > 800 - 64)
			position.x = 800 - 64;

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(platform.getTexture(),
				position.x , position.y ,
				origin.x, origin.y,
				dimension.x / 4, dimension.y,
				scale.x, scale.y,
				rotation,
				platform.getRegionX(), platform.getRegionY(),
				platform.getRegionWidth(), platform.getRegionHeight(),
				false, false);
	}
}
