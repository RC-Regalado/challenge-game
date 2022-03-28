package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rc.fortress.utils.Animation;
import com.rc.fortress.utils.Assets;

public class Muffin extends DynamicObject {
    private final Assets.AssetMuffin skin;


    public static final int MUFFIN_STATE_JUMP = 0;
    public static final int MUFFIN_STATE_FALL = 1;
    public static final int MUFFIN_STATE_HIT = 2;
    public static final float MUFFIN_JUMP_VELOCITY = 11;
    public static final float MUFFIN_MOVE_VELOCITY = 20;
    public static final float MUFFIN_WIDTH = 75f;
    public static final float MUFFIN_HEIGHT = 75f;

    public Muffin(float x, float y) {
        super(x, y, MUFFIN_WIDTH, MUFFIN_HEIGHT);

        skin = Assets.instance.assetMuffin;
    }

    public void right(){

    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion region = skin.walking.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);

        batch.draw(region, position.x, position.y, bounds.width, bounds.height);
    }
}
