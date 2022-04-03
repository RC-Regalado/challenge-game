package com.rc.fortress.game.weapons;

import com.rc.fortress.Fortress;
import com.rc.fortress.utils.Assets;

public class Mace extends Weapon{

    public Mace(float x, float y) {
        super(WeaponTypes.MACE, x, y);
        skin =  Fortress.assets.assetMace.attack;
    }
}
