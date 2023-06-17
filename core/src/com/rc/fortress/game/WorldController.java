package com.rc.fortress.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class WorldController extends InputAdapter{
	private static final String TAG = WorldController.class.getName();

	public WorldController() {
		init();
	}

	private void init () {
		Gdx.input.setInputProcessor(this);
	}
	public void update (float deltaTime) {}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.R){
			init();
			Gdx.app.debug(TAG, "World resetted!");
		}

		return false;
	}
}
