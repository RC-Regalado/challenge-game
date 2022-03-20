package com.rc.fortress;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rc.fortress.game.Muffin;
import com.rc.fortress.utils.Assets;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Muffin muffin;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		muffin = new Muffin(0, 0, 150, 150);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		muffin.update(Gdx.graphics.getDeltaTime());

		batch.begin();
		muffin.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
