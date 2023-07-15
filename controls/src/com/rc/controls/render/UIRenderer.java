package com.rc.controls.render;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rc.controls.Assets;
import com.rc.controls.Assets.UISkinAsset;
import com.rc.controls.UI;
import com.rc.controls.UIController;

public class UIRenderer {
	private UISkinAsset uiAsset;
	private UI uiData;

	public UIRenderer(UIController controller) {
		Assets.controls.init(new AssetManager());
		init();
	}

	public void init() {
		uiAsset = Assets.controls.uiSkin;
		uiData = new UI();
	}

	public void update(float deltaTime) {

	}

	public void render(SpriteBatch batch) {
		batch.draw(uiAsset.border,
				uiData.getX(), uiData.getY(),
				uiData.dimension.width, uiData.dimension.height);

		batch.draw(uiAsset.guide,
				uiData.cursor.x, uiData.cursor.y,
				uiData.cursor.width, uiData.cursor.height);
	}
}
