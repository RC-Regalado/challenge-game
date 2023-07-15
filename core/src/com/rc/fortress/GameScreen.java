package com.rc.fortress;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rc.fortress.game.WorldController;
import com.rc.fortress.game.render.WorldRenderer;
import com.rc.fortress.utils.Assets;

public class GameScreen implements Screen {
	private WorldController worldController;
	private WorldRenderer worldRenderer;

	public GameScreen(final Fortress game) {
		Assets.assets.init(new AssetManager());

		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController, game);

	}

	@Override
	public void render(float delta) {
		worldController.update(delta);
		ScreenUtils.clear(0, 0, 0.2f, 1);

		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
	}
}
