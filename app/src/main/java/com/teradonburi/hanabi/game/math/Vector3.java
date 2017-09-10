package com.teradonburi.hanabi.game.math;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/09.
 */

public class Vector3 {

    public float[] v = new float[3];

    @Inject
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

    public static Vector3 add(Vector3 a,Vector3 b){
        return new Vector3(a.getX() + b.getX(),a.getY() + b.getY(),a.getZ() + b.getZ());
    }

    public static Vector3 sub(Vector3 a,Vector3 b){
        return new Vector3(a.getX() - b.getX(),a.getY() - b.getY(),a.getZ() - b.getZ());
    }

    public static Vector3 mul(Vector3 vec3,float s){
        return new Vector3(vec3.getX() * s,vec3.getY() * s,vec3.getZ() * s);
    }

    public static Vector3 div(Vector3 vec3,float s){
        return new Vector3(vec3.getX() / s,vec3.getY() / s,vec3.getZ() / s);
    }

    public static float dot(Vector3 a, Vector3 b){
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    public static Vector3 cross(Vector3 a, Vector3 b){
        return new Vector3(
                a.getY() * b.getZ() - a.getZ() * b.getY(),
                a.getZ() * b.getX() - a.getX() * b.getZ(),
                a.getX() * b.getY() - a.getY() * b.getX()
        );
    }

    public float getX(){
        return v[0];
    }

    public float getY(){
        return v[1];
    }

    public float getZ(){
        return v[2];
    }

    public float[] getValue(){
        return v;
    }

}
