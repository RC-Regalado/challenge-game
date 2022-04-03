package com.rc.fortress.game.weapons;

import com.rc.fortress.Fortress;
import com.rc.fortress.utils.Assets;

public class Sabre extends Weapon {

    public Sabre(float x, float y) {
        super(WeaponTypes.SABRE, x, y);
        this.skin =  Fortress.assets.assetSabre.attack;
    }
}
