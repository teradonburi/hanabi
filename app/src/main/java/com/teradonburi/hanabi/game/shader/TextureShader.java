package com.teradonburi.hanabi.game.shader;

import android.opengl.GLES20;
import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/10.
 */

@Lifecycle
public class TextureShader extends Shader {

    @Inject
    public TextureShader(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void loadShader() {
        load("vertex_tex.shader", "fragment_tex.shader");
    }

    @Override
    public void bindAttributes() {
        bindAttribute(0,"position");
        bindAttribute(1,"texcoord");
    }

    // テクスチャフィルタリング(ジャギ補正)
    public void setTextureFiltering(){
        // 線形フィルタリング
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
    }

    // ブレンディングモードの指定
    public void enableBlending(boolean enable){

        if(enable){
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        }else{
            GLES20.glDisable(GLES20.GL_BLEND);
        }
    }

    public int getAttributePosition(){
        return getAttribute("position");
    }

    public int getAttributeTexcoord(){
        return getAttribute("texcoord");
    }

    public int getUniformTexture(){
        return getUniform("texture");
    }


}
