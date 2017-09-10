package com.teradonburi.hanabi.game.geometory;


import com.teradonburi.hanabi.game.shader.Shader;
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
    protected VertexBuffer vertexBuffer;
    protected IndexBuffer indexBuffer;
    protected TextureBuffer textureBuffer;
    protected static Matrix44 MVPMatrix;

    public Geometory(){
        id = UUID.randomUUID().toString();
        vertexBuffer = new VertexBuffer();
        indexBuffer = new IndexBuffer();
        textureBuffer = new TextureBuffer();
    }

    public String getId(){
        return id;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public abstract void draw();

    public static void setMVPMatrix(Matrix44 MVPMatrix) {
        Geometory.MVPMatrix = MVPMatrix;
    }
}
