package com.rc.fortress.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    private Assets(){}

    public void init(AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
//        assetManager.load();
        assetManager.finishLoading();
    }

    public class AssetStarkMan {
        public final Animation jumping;

        public AssetStarkMan(TextureAtlas atlas){
            List<TextureRegion> tmp = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                tmp.add(atlas.findRegion("jumping-" + i));
            }

            jumping = new Animation(0.2f, tmp);
        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

    }

    @Override
    public void dispose() {

    }
}
