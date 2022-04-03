package com.rc.fortress;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rc.fortress.game.Muffin;
import com.rc.fortress.game.Platform;
import com.rc.fortress.game.weapons.Mace;
import com.rc.fortress.game.weapons.Sabre;
import com.rc.fortress.game.weapons.Weapon;
import com.rc.fortress.utils.Assets;

public class Fortress extends Game {
	SpriteBatch batch;
	Texture img;
	Muffin muffin;
	Platform platform;

	Weapon weapon;

	public static Assets assets;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_ERROR);

		assets = new Assets(new AssetManager());
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		muffin = new Muffin(100, 30);
		platform = new Platform(10, 10, 200);

		weapon = new Mace(25, 25);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		muffin.update(Gdx.graphics.getDeltaTime());
		weapon.update(Gdx.graphics.getDeltaTime());

		batch.begin();
		platform.render(batch);
		muffin.render(batch);
		weapon.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
