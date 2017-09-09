package com.teradonburi.hanabi.game.geometory;

import com.teradonburi.hanabi.game.Shader;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class Triangle extends Geometory
{
    private final Shader shader;

    @Inject
    public Triangle(Shader shader){
        this.shader = shader;
    }

    public void shaderLoad(){
        shader.load("vertex.shader","fragment.shader");
    }

    @Override
    public void draw(){
        this.shader.attach();
        this.shader.drawTriangle();
    }


}
