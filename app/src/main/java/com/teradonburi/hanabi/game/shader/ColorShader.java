package com.teradonburi.hanabi.game.shader;

import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/10.
 */

@Lifecycle
public class ColorShader extends Shader{

    @Inject
    public ColorShader(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void loadShader() {
        load("color.vert", "color.frag");
    }

    @Override
    public void bindAttributes() {
        bindAttribute(0,"a_Position");
        bindAttribute(1,"a_Color");
    }

    public int getUniformMVPMatrix(){
        return getUniform("u_MVPMatrix");
    }
    public int getAttributePosition(){
        return getAttribute("a_Position");
    }
    public int getAttributeColor(){
        return getAttribute("a_Color");
    }

}
