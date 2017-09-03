package com.teradonburi.hanabi.entity;

import java.util.UUID;

/**
 * Created by daiki on 2017/09/03.
 */

public class UserEntity implements Entity {

    public String id;
    public String name;

    public static String createUserID(){
        return UUID.randomUUID().toString();
    }
}
