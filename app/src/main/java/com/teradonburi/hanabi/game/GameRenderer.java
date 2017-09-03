package com.teradonburi.hanabi.game;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.teradonburi.hanabi.game.geometory.Geometory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by daiki on 2017/09/03.
 */

public class GameRenderer implements GLSurfaceView.Renderer {

    private Map<String,Geometory> geometories = new HashMap<>();
    private GameRendererEvent rendererEvent;

    @Inject
    public GameRenderer(){}

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        if (rendererEvent != null) {
            rendererEvent.onSurfaceCreated();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        // 背景色(R,G,B,ALPHA)
        GLES20.glClearColor(0.0f, 0.0f, 1.0f, 1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        for(Map.Entry<String,Geometory> entry:geometories.entrySet()){
            entry.getValue().draw();
        }
    }

    public void setRendererEvent(GameRendererEvent rendererEvent) {
        this.rendererEvent = rendererEvent;
    }


    public void addGeometory(Geometory geometory){
        geometories.put(geometory.getId(),geometory);
    }

    public void removeGeometory(Geometory geometory){
        if(geometories.containsKey(geometory.getId())){
            geometories.remove(geometory.getId());
        }
    }

    public void clearGeometory(){
        geometories.clear();
    }

}
