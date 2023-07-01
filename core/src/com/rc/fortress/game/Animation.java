package com.rc.fortress.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	public static final int ANIMATION_LOOPING = 0;
	public static final int ANIMATION_NONLOOPING = 1;

	private TextureRegion[] keyFrames;
	private int last;
	final float frameDuration;

	public Animation (float frameDuration, int frames) {
		this.frameDuration = frameDuration;
		this.keyFrames = new TextureRegion[frames];
		last = 0;
	}

	public Animation addFrame(TextureRegion frame){
		if (last > keyFrames.length){
			throw new RuntimeException("Too much frames.");
		}

		this.keyFrames[last++] = frame;

		return this;
	}

	public TextureRegion getKeyFrame (float stateTime, int mode) {
		int frameNumber = (int)(stateTime / frameDuration);

		if (mode == ANIMATION_NONLOOPING) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}
}
