package com.rc.fortress.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();

    private AssetManager assetManager;

    // Media de los objetos
    public AssetMuffin assetMuffin;
    public AssetPlatform assetPlatform;

    // Armas
    public AssetWeapon assetSabre;
    public AssetWeapon assetMace;

    public Assets(AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        init();
    }

    public void init(){
        assetManager.load(Constants.ATLAS, TextureAtlas.class);
        assetManager.load(Constants.MACE, TextureAtlas.class);
        assetManager.load(Constants.SABRE, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.ATLAS);
        TextureAtlas mace = assetManager.get(Constants.MACE);
        TextureAtlas sabre = assetManager.get(Constants.SABRE);

//        for (Texture t : atlas.getTextures())
//            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        assetMuffin = new AssetMuffin(atlas);
        assetPlatform = new AssetPlatform(atlas);

        assetMace = new AssetMace(mace);
        assetSabre = new AssetSabre(sabre);
    }

    public static class AssetMuffin {
        public final Animation walking;
        public final TextureRegion jumping;

        public AssetMuffin(TextureAtlas atlas){
            TextureRegion[] tmp = new TextureRegion[6];
            for (int i = 0; i < tmp.length; i++)
                tmp[i] = atlas.findRegion("muffin-" + (i+1));


            walking = new Animation(0.12f, tmp);
            jumping = new TextureRegion(atlas.findRegion("muffin-jump"));
        }
    }

    public static class AssetPlatform {
        public final TextureRegion platform;

        public AssetPlatform(TextureAtlas atlas){
            platform = new TextureRegion(atlas.findRegion("platform"));
        }
    }

    public static abstract class AssetWeapon {
        public final Animation attack;

        public AssetWeapon(TextureAtlas atlas, int steps, String name){
            TextureRegion[] tmp = new TextureRegion[steps];

            for (int i = 0; i < steps; i++)
                tmp[i] = atlas.findRegion(name + '-' + (i+1));

            attack = new Animation(0.18f, tmp);
        }
    }

    public static class AssetMace extends AssetWeapon{
        public AssetMace(TextureAtlas atlas) {
            super(atlas, 5, "mace");
        }
    }

    public static class AssetSabre extends AssetWeapon{
        public AssetSabre(TextureAtlas atlas) {
            super(atlas, 5, "sabre");
        }
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Loading error.", throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
