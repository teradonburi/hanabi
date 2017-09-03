package com.teradonburi.hanabi.game;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.teradonburi.hanabi.entity.Entity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by daiki on 2017/09/03.
 */

@Singleton
public class SaveData {

    private final SharedPreferences preferences;
    private final Gson gson;

    @Inject
    public SaveData(SharedPreferences preferences, Gson gson){
        this.preferences = preferences;
        this.gson = gson;
    }

    public void save(Entity entity){
        this.preferences.edit()
                .putString(entity.getClass().getSimpleName(),gson.toJson(entity))
                .commit();
    }

    public <T> T load(Class<T> entityClass){
        String json = this.preferences.getString(entityClass.getSimpleName(),null);
        return gson.fromJson(json,entityClass);
    }

    public boolean isExist(Class entityClass){
        return this.preferences.contains(entityClass.getSimpleName());
    }

}
