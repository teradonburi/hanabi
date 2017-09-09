package com.teradonburi.hanabi.game.geometory;


import com.teradonburi.hanabi.game.Shader;
import com.teradonburi.hanabi.game.math.Matrix44;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.UUID;

/**
 * Created by daiki on 2017/09/03.
 */

public abstract class Geometory {

    private String id;
    protected Shader shader;
    protected static Matrix44 MVPMatrix;

    public static void setMVPMatrix(Matrix44 MVPMatrix) {
        Geometory.MVPMatrix = MVPMatrix;
    }

    public Geometory(){
        id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public static final int BytesPerFloat = 4; // floatのバイト数
    protected FloatBuffer vertexBuffer;
    public void setVertexBuffer(float vertices[]){
        vertexBuffer = ByteBuffer.allocateDirect(vertices.length * BytesPerFloat)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }


    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public abstract void draw();
}
