package com.rc.fortress.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.SortedIntList.Iterator;
import com.rc.fortress.Fortress;
import com.rc.fortress.game.objects.Platform;
import com.rc.fortress.utils.Assets;
import com.rc.fortress.utils.Constants;

public class WorldRenderer implements Disposable {
	final Fortress game;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController controller;

	private TextureRegion platformImg;

	private Array<Rectangle> raindrops;
	private float state;

	public WorldRenderer(WorldController worldController, final Fortress game) {
		this.game = game;
		this.controller = worldController;
		init();
	}

	private void init() {
		batch = game.batch;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);

		camera.update();
		raindrops = new Array<Rectangle>();
		spawnRaindrop();

		platformImg = Assets.assets.platform.platform;
		state = 0;
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;

		raindrop.width = 64;
		raindrop.height = 64;

		raindrops.add(raindrop);
	}

	public void renderPlatform(SpriteBatch batch) {
		for (Platform platform : controller.activePlatforms) {
			batch.draw(platformImg,
					platform.position.x, platform.position.y,
					platform.dimension.width, platform.dimension.height);
		}
	}

	public void renderMuffin(SpriteBatch batch) {
		batch.draw(Assets.assets.muffin.bread.getKeyFrame(state, 0), 0, 0);
	}

	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.disableBlending();
		batch.draw(Assets.assets.background, 
				0, 0,
				Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		batch.enableBlending();

		renderPlatform(batch);
		renderMuffin(batch);
		batch.end();

		state += Gdx.graphics.getDeltaTime();
	}

	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
	}
}
