package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rc.fortress.utils.Animation;
import com.rc.fortress.utils.Assets;

public class Muffin extends DynamicGameObject{
    private final Assets.AssetMuffin skin;


    public Muffin(float x, float y, float width, float height) {
        super(x, y, width, height);

        skin = Assets.instance.assetMuffin;
    }

    public void right(){

    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion region = skin.walking.getKeyFrame(delta, Animation.ANIMATION_LOOPING);

        batch.draw(region, position.x, position.y, bounds.width, bounds.height);
    }
}
