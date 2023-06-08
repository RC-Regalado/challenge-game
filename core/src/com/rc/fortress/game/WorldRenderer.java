package com.rc.fortress.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController controller;
	
	public WorldRenderer (WorldController worldController){
		this.controller = worldController;
		init();
	}

	private void init() {
		batch = new SpriteBatch();

	}

	public void render () {}
	public void resize(int width, int height) {}

	@Override
	public void dispose() {

	}
}
