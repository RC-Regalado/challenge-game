package com.rc.fortress.game.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rc.fortress.game.objects.UI;
import com.rc.fortress.utils.Assets;
import com.rc.fortress.utils.Assets.UISkinAsset;

public class UIRenderer {
	private UISkinAsset uiAsset;
	private UI uiData;

	public UIRenderer(){
		init();
	}

	public void init(){
		uiAsset = Assets.assets.uiSkin;
		uiData = new UI();
	}
	public void update(float deltaTime){

	}

	public void render(SpriteBatch batch){
		batch.draw(uiAsset.border, 
				uiData.getX(), uiData.getY(),
				uiData.dimension.width, uiData.dimension.height
				);

		batch.draw(uiAsset.guide,
				uiData.cursor.x, uiData.cursor.y,
				uiData.cursor.width, uiData.cursor.height
				);
	}
}
