package com.teradonburi.hanabi.game.math;

import android.opengl.Matrix;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/09.
 */

public class Matrix44 {

    public float[] m = new float[16];

    @Inject
    public Matrix44(){
        identity();
    }

    public Matrix44 identity(){
        Matrix.setIdentityM(m,0);
        return this;
    }

    public Matrix44 translate(float x,float y,float z){
        Matrix.translateM(m,0,x,y,z);
        return this;
    }

    public Matrix44 translate(Vector3 vec3){
        return translate(vec3.getX(),vec3.getY(),vec3.getZ());
    }

    public Matrix44 rotateX(float degree){
        Matrix.rotateM(m, 0, degree, 1.0f, 0.0f, 0.0f);
        return this;
    }

    public Matrix44 rotateY(float degree){
        Matrix.rotateM(m, 0, degree, 0.0f, 1.0f, 0.0f);
        return this;
    }

    public Matrix44 rotateZ(float degree){
        Matrix.rotateM(m, 0, degree, 0.0f, 0.0f, 1.0f);
        return this;
    }

    public Matrix44 mul(Matrix44 mat44){
        Matrix.multiplyMM(m, 0, mat44.m, 0, m, 0);
        return this;
    }

    public static Matrix44 createViewMatrix(Vector3 eye,Vector3 look,Vector3 up){
        Matrix44 viewMatrix = new Matrix44();
        Matrix.setLookAtM(viewMatrix.m, 0,
                eye.getX(), eye.getY(), eye.getZ(),
                look.getX(), look.getY(), look.getZ(),
                up.getX(), up.getY(), up.getZ());
        return viewMatrix;
    }

    public static Matrix44 createProjectionMatrix(float aspect,float near,float far){
        final float left = -aspect;
        final float right = aspect;
        final float bottom = -1.0f;
        final float top = 1.0f;

        Matrix44 projMatrix = new Matrix44();
        Matrix.frustumM(projMatrix.m, 0, left, right, bottom, top, near, far);
        return projMatrix;
    }

}
