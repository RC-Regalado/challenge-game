package com.rc.fortress;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.rc.fortress.game.WorldController;
import com.rc.fortress.game.WorldRenderer;
import com.rc.fortress.game.objects.Platform;
import com.rc.fortress.utils.Assets;

public class GameScreen implements Screen {
	final Fortress game;

	private OrthographicCamera camera;

	private Platform platform;

	private Texture dropImg;
	private Texture bucketImg;

	private Rectangle bucket;

	private Sound dropSound;
	private Music lofiMusic;

	private Array<Rectangle> raindrops;
	private long lastDropTime;

	private WorldController worldController;
	private WorldRenderer worldRenderer;

	int dropsGathered;

	private FileHandle read(String file) {
		return Gdx.files.internal(file);
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;

		raindrop.width = 64;
		raindrop.height = 64;

		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	public GameScreen(final Fortress game) {
		Assets.assets.init(new AssetManager());

		this.game = game;
		dropsGathered = 0;

		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;

		bucket.width = 64;
		bucket.height = 64;

		dropImg = new Texture(read("drop.png"));
		bucketImg = new Texture(read("bucket.png"));

		dropSound = Assets.assets.sounds.dropSound;
		lofiMusic = Assets.assets.music.lofiMusic;

		lofiMusic.setLooping(true);

		platform = new Platform();
		platform.position.set(10, 10);
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render(float delta) {
		worldController.update(delta);

		ScreenUtils.clear(0, 0, 0.2f, 1);

		worldRenderer.render();

		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		platform.render(game.batch);
		for (Rectangle raindrop : raindrops) {
			game.batch.draw(dropImg, raindrop.x, raindrop.y);
		}
		game.batch.end();

		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		Iterator<Rectangle> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();

			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.overlaps(bucket)) {
				dropsGathered++;
				dropSound.play();
				iter.remove();
			} else if (raindrop.y + 64 < 0) {
				iter.remove();
			}
		}

		platform.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
		lofiMusic.play();
	}

	@Override
	public void hide() {
		lofiMusic.stop();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		dropImg.dispose();
		bucketImg.dispose();

		dropSound.dispose();
		lofiMusic.dispose();
		worldRenderer.dispose();
	}
}
