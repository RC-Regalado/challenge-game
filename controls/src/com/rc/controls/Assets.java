package com.rc.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.rc.controls.utils.Constants;

public class Assets implements Disposable, AssetErrorListener {
	private static final String TAG = Assets.class.getName();

	public static final Assets controls = new Assets();
	private AssetManager assetManager;

	public UISkinAsset uiSkin;

	private Assets() {
	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;

		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_GUI, TextureAtlas.class);

		assetManager.finishLoading();

		TextureAtlas ui = assetManager.get(Constants.TEXTURE_ATLAS_GUI);
		uiSkin = new UISkinAsset(ui);
	}

	public class UISkinAsset {
		public final TextureRegion border;
		public final TextureRegion guide;

		public UISkinAsset (TextureAtlas atlas) {
			border = atlas.findRegion("directional-border");
			guide = atlas.findRegion("directional-guide");
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
