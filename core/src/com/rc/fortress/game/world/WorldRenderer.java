package com.rc.fortress.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
    private  LevelController level;

    static final float FRUSTUM_WIDTH = 10;
    static final float FRUSTUM_HEIGHT = 15;
    OrthographicCamera cam;
    SpriteBatch batch;

    public WorldRenderer(WorldController controller) {
//        this.level = level;
//        this.batch = batch;
    }

    public void render(){

    }

    private void renderBackground(){
        batch.disableBlending();
        batch.begin();

        batch.end();
    }

    private void renderObjects(){
        batch.enableBlending();
        batch.begin();

        level.muffin.render(batch);


        batch.end();
    }
}
