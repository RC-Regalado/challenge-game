package com.rc.fortress.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.rc.controls.UI;
import com.rc.controls.UIController;
import com.rc.fortress.game.objects.Platform;
import com.rc.fortress.utils.Assets;

public class WorldController extends InputAdapter implements Disposable {
	private static final String TAG = WorldController.class.getName();

	private Music lofiMusic;

	public Array<Platform> activePlatforms;
	private final Pool<Platform> platformPool;

	public UIController uiController;

	public WorldController() {
		platformPool = new Pool<Platform>() {
			@Override
			protected Platform newObject() {
					return new Platform();
			}
		};

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);

		init();
	}

	private void init () {
		uiController = new UIController();

		lofiMusic = Assets.assets.music.lofiMusic;

		lofiMusic.setLooping(true);
		activePlatforms = new Array<>();

		Platform platform = platformPool.obtain();
		platform.dimension.setSize(150, 50);

		activePlatforms.add(platform);
	}

	public void update (float deltaTime) {
		uiController.update(deltaTime);

		for(Platform platform : activePlatforms) {
			platform.update(deltaTime);
		}
	}


	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.R){
			init();
			Gdx.app.debug(TAG, "World resetted!");
		}

		return false;
	}
	@Override
	public void dispose() {
		lofiMusic.dispose();
	}
}
