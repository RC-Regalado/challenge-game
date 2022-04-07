package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rc.fortress.Fortress;
import com.rc.fortress.utils.Animation;
import com.rc.fortress.utils.Assets;

public class Muffin extends DynamicObject {
    private final Assets.AssetMuffin skin;

    public enum MuffinState {
        JUMP,
        FALL,
        HIT
    }

    MuffinState state;
    public static final float MUFFIN_JUMP_VELOCITY = 11;
    public static final float MUFFIN_MOVE_VELOCITY = 20;
    public static final float MUFFIN_WIDTH = 75f;
    public static final float MUFFIN_HEIGHT = 75f;



    public Muffin(float x, float y) {
        super(x, y, MUFFIN_WIDTH, MUFFIN_HEIGHT);

        skin =  Fortress.assets.assetMuffin;
    }

    public void right(){

    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion keyFrame;
        switch (state) {
            case FALL:
                keyFrame = skin.walking.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);
                break;
            case JUMP:
            default:
                keyFrame = skin.jumping;
                break;
        }

        float side = velocity.x < 0 ? -1 : 1;
        if (side < 0)
            batch.draw(keyFrame, position.x + 0.5f, position.y - 0.5f, side * 1, 1);
        else
            batch.draw(keyFrame, position.x - 0.5f, position.y - 0.5f, side * 1, 1);
    }

    public void hitPlatform () {
        velocity.y = MUFFIN_JUMP_VELOCITY;
        state = MuffinState.JUMP;

        stateTime = 0;
    }

    public boolean is(MuffinState state){
        return this.state == state;
    }

}
