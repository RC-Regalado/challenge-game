package com.rc.fortress.game.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rc.fortress.game.GameObject;
import com.rc.fortress.utils.Animation;


public abstract class Weapon extends GameObject {
    public enum WeaponTypes {
        MACE,
        SABRE
    }

    public static float WEAPON_WIDTH = 110f;
    public static float WEAPON_HEIGHT = 110f;
    protected final WeaponTypes type;

    protected Animation skin;

    public Weapon(WeaponTypes type, float x, float y) {
        super(x, y, WEAPON_WIDTH, WEAPON_HEIGHT);

        this.type = type;
    }

    public static Weapon generateWeapon(float probability, float x, float y){
        if (probability > 0.5f)
            return new Mace(x, y);
        else
            return new Sabre(x, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion region = skin.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);

        batch.draw(region, position.x, position.y, bounds.width, bounds.height);
    }
}
