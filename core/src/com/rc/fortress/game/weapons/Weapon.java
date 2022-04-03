package com.rc.fortress.game.weapons;

import com.rc.fortress.game.GameObject;


public abstract class Weapon extends GameObject {
    public enum WeaponTypes {
        MACE,
        SABRE
    }

    public static float WEAPON_WIDTH = 90f;
    public static float WEAPON_HEIGHT = 90f;
    protected final WeaponTypes type;

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
}
