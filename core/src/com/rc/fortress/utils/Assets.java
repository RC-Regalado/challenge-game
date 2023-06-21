package com.rc.fortress.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
	private static final String TAG = Assets.class.getName();

	public static final Assets assets = new Assets();
	private AssetManager assetManager;

	public AssetsMusic music;
	public AssetsSound sounds;
	public AssetMuffin muffin;
	public AssetPlatform platform;

	private Assets() {
	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;

		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);

		assetManager.load("lofi.mp3", Music.class);
		assetManager.load("drop.wav", Sound.class);
		assetManager.finishLoading();

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

		muffin = new AssetMuffin(atlas);
		platform = new AssetPlatform(atlas);

		music = new AssetsMusic(assetManager);
		sounds = new AssetsSound(assetManager);
	}

	public class AssetMuffin {
		public final AtlasRegion bread;

		public AssetMuffin (TextureAtlas atlas){
			bread = atlas.findRegion("muffin-1");
		}
	}

	public class AssetPlatform {
		public final AtlasRegion platform;

		public AssetPlatform(TextureAtlas atlas) {
			platform = atlas.findRegion("platform");
		}
	}

	public class AssetsSound {
		public final Sound dropSound;

		public AssetsSound(AssetManager manager) {
			dropSound = manager.get("drop.wav", Sound.class);
		}
	}

	public class AssetsMusic {
		public final Music lofiMusic;

		public AssetsMusic(AssetManager manager) {
			lofiMusic = manager.get("lofi.mp3", Music.class);
		}

	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	@Override
	public void error(AssetDescriptor descriptor, Throwable throwable) {
		Gdx.app.error(TAG, "Asset error " + descriptor.fileName, (Exception) throwable);
	}
}
