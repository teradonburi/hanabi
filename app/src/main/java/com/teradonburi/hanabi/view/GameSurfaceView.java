package com.teradonburi.hanabi.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;


/**
 * Created by daiki on 2017/09/03.
 */

public class GameSurfaceView extends GLSurfaceView
{

    public GameSurfaceView(Context context) {
        super(context);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        setEGLContextClientVersion(2);
    }

    public void setRender(GLSurfaceView.Renderer renderer){
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

}
