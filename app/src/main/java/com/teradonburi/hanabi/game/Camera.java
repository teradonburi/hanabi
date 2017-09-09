package com.teradonburi.hanabi.game;

import android.opengl.Matrix;


/**
 * Created by daiki on 2017/09/09.
 */

public class Camera
{
    private float[] mViewMatrix = new float[16]; // ビュー行列

    private float[] eye = {0.0f, 0.0f, 1.5f};   // カメラの視点
    private float[] look = {0.0f, 0.0f, -5.0f}; // カメラの注視点
    private float[] up = {0.0f, 1.0f, 0.0f};    // カメラの上方向

    public void Camera(){
        // カメラ(ビュー行列)を設定
    }

    public void setEye(float x,float y,float z){
    }

    public void setLookAt(float x,float y,float z){

    }

    public void setUp(float x,float y,float z){

    }

    public void update(){

        Matrix.setLookAtM(mViewMatrix, 0,
                eye[0], eye[1], eye[2],
                look[0], look[1], look[2],
                up[0], up[1], up[2]);
    }

}
