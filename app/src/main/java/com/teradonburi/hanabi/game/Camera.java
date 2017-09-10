package com.teradonburi.hanabi.game;

import android.opengl.Matrix;

import com.teradonburi.hanabi.game.math.Matrix44;
import com.teradonburi.hanabi.game.math.Vector3;

import javax.inject.Inject;


/**
 * Created by daiki on 2017/09/09.
 */

public class Camera
{
    private Matrix44 viewMatrix = new Matrix44(); // ビュー行列
    private Vector3 eye = new Vector3(0.0f, 0.0f, 1.5f);   // カメラの視点
    private Vector3 look = new Vector3(0.0f, 0.0f, -5.0f); // カメラの注視点
    private Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);    // カメラの上方向

    @Inject
    public void Camera(){
    }

    public void setEye(Vector3 eye) {
        this.eye = eye;
    }

    public void setLook(Vector3 look) {
        this.look = look;
    }

    public void setUp(Vector3 up) {
        this.up = up;
    }

    public void update(){
        // カメラ(ビュー行列)を設定
        viewMatrix = Matrix44.createViewMatrix(eye,look,up);
    }

    public Matrix44 getViewMatrix(){
        return viewMatrix;
    }

}
