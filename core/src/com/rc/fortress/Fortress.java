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
import com.rc.fortress.game.world.WorldController;
import com.rc.fortress.game.world.WorldRenderer;
import com.rc.fortress.utils.Assets;
import com.rc.fortress.views.MainMenuScreen;

public class Fortress extends Game {

	public SpriteBatch batch;
	public static Assets assets;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_ERROR);

		assets = new Assets(new AssetManager());
		setScreen(new MainMenuScreen());
	}

	@Override
	public void render () {
		super.render();
	}
}
