package com.rc.fortress.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.List;

public class Animation {	public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;

    final List<TextureRegion> keyFrames;
    final float frameDuration;

    public Animation (float frameDuration, List<TextureRegion> keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }

    public TextureRegion getKeyFrame (float stateTime, int mode) {
        int frameNumber = (int)(stateTime / frameDuration);

        if (mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.size() - 1, frameNumber);
        } else {
            frameNumber = frameNumber % keyFrames.size();
        }
        return keyFrames.get(frameNumber);
    }
}
