package com.teradonburi.hanabi.game.geometory;

import android.opengl.GLES20;

import com.teradonburi.hanabi.game.shader.ColorShader;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class Triangle extends Geometory
{

    private static final int VERTEX_STRIDE = 7;
    private static final int POS_CURSOR = 0;
    private static final int COLOR_CURSOR = 4;
    private static final int POS_SIZE = 3;
    private static final int COLOR_SIZE = 4;

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
        vertexBuffer.create(vertices);
    }

    @Override
    public void draw(){
        shader.attach();

        if(shader instanceof ColorShader){
            ColorShader colorShader = (ColorShader) shader;

            // ハンドル(ポインタ)の取得
            int handleMVPMatrix = colorShader.getUniformMVPMatrix();
            int handlePos = colorShader.getAttributePosition();
            int handleColor = colorShader.getAttributeColor();

            // OpenGLに頂点バッファを渡す
            vertexBuffer.setPosition(POS_CURSOR);  // 頂点バッファを座標属性にセット
            GLES20.glVertexAttribPointer(handlePos, POS_SIZE, GLES20.GL_FLOAT, false, VERTEX_STRIDE * VertexBuffer.BytesPerFloat, vertexBuffer.getBuffer());  // ポインタと座標属性を結び付ける
            GLES20.glEnableVertexAttribArray(handlePos);  // 座標属性有効

            vertexBuffer.setPosition(COLOR_CURSOR);  // 頂点バッファを色属性にセット
            GLES20.glVertexAttribPointer(handleColor, COLOR_SIZE, GLES20.GL_FLOAT, false, VERTEX_STRIDE * VertexBuffer.BytesPerFloat, vertexBuffer.getBuffer());  // ポインタと色属性を結び付ける
            GLES20.glEnableVertexAttribArray(handleColor);  // 色属性有効

            // ワールド行列×ビュー行列×射影行列をセット
            GLES20.glUniformMatrix4fv(handleMVPMatrix, 1, false, MVPMatrix.m, 0);

            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);  // 三角形を描画
        }

    }

}
