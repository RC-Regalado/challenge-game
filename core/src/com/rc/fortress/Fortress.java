package com.rc.fortress;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Fortress extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Texture dropImg;
	private Texture bucketImg;

	private Rectangle bucket;

	private Sound dropSound;
	private Music lofiMusic;

	private FileHandle read(String file){
		return Gdx.files.internal(file);
	}
	@Override
	public void create () {
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
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImg, bucket.x, bucket.y);
		batch.end();

		if(Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.x = touchPos.x - 64 / 2;
   }

	}
}
