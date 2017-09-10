package com.teradonburi.hanabi.game.geometory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/**
 * Created by daiki on 2017/09/10.
 */

public class IndexBuffer {

    public static final int BytesPerShort = 2; // shortのバイト数
    protected ShortBuffer buffer;


    public void create(short[] indices) {
        buffer = ByteBuffer.allocateDirect(indices.length * BytesPerShort)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer();
        buffer.put(indices).position(0);
    }

    public int getCapacity(){
        return buffer.capacity();
    }

    public ShortBuffer getBuffer(){
        return buffer;
    }


}
