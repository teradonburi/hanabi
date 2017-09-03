package com.teradonburi.hanabi.game.geometory;

import java.util.UUID;

/**
 * Created by daiki on 2017/09/03.
 */

public abstract class Geometory {

    private String id;

    public Geometory(){
        id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public abstract void draw();
}
