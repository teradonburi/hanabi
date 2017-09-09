package com.teradonburi.hanabi.game;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by daiki on 2017/09/03.
 */

public interface GameRendererEvent {

    void onGameInit();

    void onSurfaceChange(GL10 gl, int width, int height);

    void onGameLoop();
}
