package com.rc.fortress;

import java.util.Iterator;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Fortress extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Texture dropImg;
	private Texture bucketImg;

	private Rectangle bucket;

	private Sound dropSound;
	private Music lofiMusic;

	private Array<Rectangle> raindrops;
	private long lastDropTime;

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

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_ERROR);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;

		bucket.width = 64;
		bucket.height = 64;

		dropImg = new Texture(read("drop.png"));
		bucketImg = new Texture(read("bucket.png"));

		dropSound = Gdx.audio.newSound(read("drop.wav"));
		lofiMusic = Gdx.audio.newMusic(read("lofi.mp3"));

		lofiMusic.setLooping(true);
		lofiMusic.play();

		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImg, bucket.x, bucket.y);
		for (Rectangle raindrop : raindrops) {
			batch.draw(dropImg, raindrop.x, raindrop.y);
		}
		batch.end();

		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			bucket.x += 200 * Gdx.graphics.getDeltaTime();

		if (bucket.x < 0)
			bucket.x = 0;
		if (bucket.x > 800 - 64)
			bucket.x = 800 - 64;

		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext();) {
			Rectangle raindrop = iter.next();

			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.overlaps(bucket)) {
				dropSound.play();
				iter.remove();
			} else if (raindrop.y + 64 < 0) {
				iter.remove();
			}
		}
	}

	@Override
	public void dispose() {
		dropImg.dispose();
		bucketImg.dispose();

		dropSound.dispose();
		lofiMusic.dispose();

		batch.dispose();
	}
}
