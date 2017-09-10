package com.teradonburi.hanabi.game.geometory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by daiki on 2017/09/10.
 */

public class VertexBuffer {

    public static final int BytesPerFloat = 4; // floatのバイト数
    protected FloatBuffer buffer;


    public void create(float vertices[]){
        buffer = ByteBuffer.allocateDirect(vertices.length * BytesPerFloat)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        buffer.put(vertices).position(0);
    }

    public void setPosition(int position){
        buffer.position(position);
    }

    public FloatBuffer getBuffer(){
        return buffer;
    }

}
