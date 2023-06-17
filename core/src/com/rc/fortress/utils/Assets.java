package com.rc.fortress.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
	private static final String TAG = Assets.class.getName();
	@Override
	public void dispose() {

	}
	@Override
	public void error (AssetDescriptor descriptor, Throwable throwable){
		Gdx.app.error(TAG, "Asset error " + descriptor.fileName, (Exception) throwable);
	}
}
