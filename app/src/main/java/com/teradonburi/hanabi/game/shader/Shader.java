package com.teradonburi.hanabi.game.shader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.khronos.opengles.GL10;


/**
 * Created by daiki on 2017/09/09.
 */

public abstract class Shader {

    private final AppCompatActivity activity;

    private int vertexShader;
    private int fragmentShader;
    protected int shaderProgram;

    public abstract void loadShader();
    public abstract void bindAttributes();

    public Shader(AppCompatActivity activity){
        this.activity = activity;
    }

    protected void load(String vertexShaderFileName,String fragmentShaderFileName){
        vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, load(vertexShaderFileName));
        fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, load(fragmentShaderFileName));
        checkShaderCompile(vertexShader);
        checkShaderCompile(fragmentShader);
        shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(shaderProgram, vertexShader);
        GLES20.glAttachShader(shaderProgram, fragmentShader);

        bindAttributes();

        GLES20.glLinkProgram(shaderProgram);
        checkShaderLink(shaderProgram);
    }

    public void attach(){
        GLES20.glUseProgram(shaderProgram);
    }


    public void bindAttribute(int index,String attr){
        GLES20.glBindAttribLocation(shaderProgram, index, attr);  // attributeのindexを設定
    }

    public int getAttribute(String attr){
        return GLES20.glGetAttribLocation(shaderProgram, attr);
    }

    public int getUniform(String uniform){
        return GLES20.glGetUniformLocation(shaderProgram,uniform);
    }

    private int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    private void checkShaderCompile(int shaderHandle){
        // コンパイル結果のチェック
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        if (compileStatus[0] == 0) {
            // コンパイル失敗
            GLES20.glDeleteShader(shaderHandle);
            throw new RuntimeException("Error creating shader.");
        }
    }

    public void checkShaderLink(int programHandle){
        // リンク結果のチェック
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

        if (linkStatus[0] == 0) {
            // リンク失敗
            GLES20.glDeleteProgram(programHandle);
            throw new RuntimeException("Error link shader.");
        }
    }

    public int loadTexture(@DrawableRes int res)
    {
        int texture[] = new int[1];
        Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), res);
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        bmp.recycle();
        return texture[0];
    }


    private String load(String shaderFileName){
        InputStream is = null;
        BufferedReader br = null;
        String code = "";

        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                is = activity.getAssets().open(shaderFileName);
                br = new BufferedReader(new InputStreamReader(is));

                // １行ずつ読み込み、改行を付加する
                String str;
                while ((str = br.readLine()) != null) {
                    code += str + "\n";
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            // エラー発生時の処理
        }

        return code;
    }


}
