package com.rc.fortress.game.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rc.fortress.utils.Animation;
import com.rc.fortress.utils.Assets;

public class Mace extends Weapon{

    private final Animation skin;
    public Mace(float x, float y) {
        super(WeaponTypes.MACE, x, y);
        skin = Assets.instance.assetMace.attack;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion region = skin.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);

        batch.draw(region, position.x, position.y, bounds.width, bounds.height);
    }
}
