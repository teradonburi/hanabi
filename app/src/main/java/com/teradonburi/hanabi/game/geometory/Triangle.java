package com.teradonburi.hanabi.game.geometory;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.teradonburi.hanabi.game.Shader;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class Triangle extends Geometory
{


    // 頂点バッファ生成
    final float[] vertices = {
            // 各頂点情報
            // (座標属性) X, Y, Z,
            // (色属性) R, G, B, A

            -0.5f, -0.25f, 0.0f,
            1.0f, 0.0f, 0.0f, 1.0f,

            0.5f, -0.25f, 0.0f,
            0.0f, 0.0f, 1.0f, 1.0f,

            0.0f, 0.559016994f, 0.0f,
            0.0f, 1.0f, 0.0f, 1.0f
    };

    @Inject
    public Triangle(){
        setVertexBuffer(vertices);
    }

    @Override
    public void draw(){
        shader.attach();

        // ハンドル(ポインタ)の取得
        int mMVPMatrixHandle = shader.getUniform("u_MVPMatrix");
        int mPositionHandle = shader.getAttribute("a_Position");
        int mColorHandle = shader.getAttribute("a_Color");


        // OpenGLに頂点バッファを渡す
        vertexBuffer.position(0);  // 頂点バッファを座標属性にセット
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 7 * 4, vertexBuffer);  // ポインタと座標属性を結び付ける
        GLES20.glEnableVertexAttribArray(mPositionHandle);  // 座標属性有効

        vertexBuffer.position(4);  // 頂点バッファを色属性にセット
        GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false, 7 * 4, vertexBuffer);  // ポインタと色属性を結び付ける
        GLES20.glEnableVertexAttribArray(mColorHandle);  // 色属性有効

        // ワールド行列×ビュー行列×射影行列をセット
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, MVPMatrix.m, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);  // 三角形を描画
    }

}
