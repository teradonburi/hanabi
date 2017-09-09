package com.teradonburi.hanabi.game;

import android.opengl.GLES20;
import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/09.
 */

@Lifecycle
public class Shader {

    private final AppCompatActivity activity;

    private int vertexShader;
    private int fragmentShader;
    private int shaderProgram;

    @Inject
    public Shader(AppCompatActivity activity){
        this.activity = activity;
    }

    public void load(String vertexShaderFileName,String fragmentShaderFileName){
        vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, load(vertexShaderFileName));
        fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, load(fragmentShaderFileName));
        shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(shaderProgram, vertexShader);
        GLES20.glAttachShader(shaderProgram, fragmentShader);
        GLES20.glLinkProgram(shaderProgram);
    }

    public void attach(){
        GLES20.glUseProgram(shaderProgram);
    }

    public void drawTriangle(){


        float vertices[] = {
                0.0f, 0.5f, 0.0f,//三角形の点A(x,y,z)
                -0.5f, -0.5f, 0.0f,//三角形の点B(x,y,z)
                0.5f, -0.5f, 0.0f//三角形の点C(x,y,z)
        };
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);


        int positionAttrib = GLES20.glGetAttribLocation(shaderProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionAttrib);
        GLES20.glVertexAttribPointer(positionAttrib, 3, GLES20.GL_FLOAT, false, 3 * 4, vertexBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertices.length/3);
        GLES20.glDisableVertexAttribArray(positionAttrib);
    }

    private int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
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
                    code += str;
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
