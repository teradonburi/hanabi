package com.teradonburi.hanabi.game.math;

/**
 * Created by daiki on 2017/09/09.
 */

public class Vector3 {

    public float[] v = new float[3];

    public Vector3(){
        this(0,0,0);
    }

    public Vector3(Vector3 vec3){
        this(vec3.v[0],vec3.v[1],vec3.v[2]);
    }

    public Vector3(float x,float y,float z){
        v[0] = x;
        v[1] = y;
        v[2] = z;
    }

    public Vector3 add(float x,float y,float z){
        v[0] += x;
        v[1] += y;
        v[2] += z;
        return this;
    }

    public Vector3 sub(float x,float y,float z){
        v[0] -= x;
        v[1] -= y;
        v[2] -= z;
        return this;
    }

    public Vector3 mul(float x,float y,float z){
        v[0] *= x;
        v[1] *= y;
        v[2] *= z;
        return this;
    }

    public Vector3 div(float x,float y,float z){
        v[0] /= x;
        v[1] /= y;
        v[2] /= z;
        return this;
    }



    public float[] getValue(){
        return v;
    }

}
