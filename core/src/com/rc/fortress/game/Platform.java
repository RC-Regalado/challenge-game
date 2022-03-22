package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rc.fortress.utils.Assets;

public class Platform extends AbstractGameObject{
    private final Assets.AssetPlatform skin;

    public Platform(float x, float y, float width, float height) {
        super(x, y, width, height);

        skin = Assets.instance.assetPlatform;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(skin.platform, position.x, position.y, bounds.width, bounds.height);
    }
}
