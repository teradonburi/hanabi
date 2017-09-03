package com.teradonburi.hanabi.game;

import android.opengl.GLSurfaceView;

import com.teradonburi.hanabi.entity.UserEntity;
import com.teradonburi.hanabi.game.geometory.Triangle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

public class GameMain implements GameRendererEvent{

    private final SaveData saveData;
    private final GameRenderer renderer;
    public UserEntity userEntity;
    private Triangle triangle;


    @Inject
    public GameMain(final SaveData saveData,final GameRenderer renderer){
        this.saveData = saveData;
        this.renderer = renderer;
        this.renderer.setRendererEvent(this);
        loadUser();
    }

    private void loadUser()
    {
        // ユーザ生成
        if(!saveData.isExist(UserEntity.class)){
            userEntity = new UserEntity();
            userEntity.id = UserEntity.createUserID();
            saveData.save(userEntity);
        }
        // ユーザロード
        else{
            userEntity = saveData.load(UserEntity.class);
        }
    }

    public GLSurfaceView.Renderer getRenderer(){
        return renderer;
    }

    @Override
    public void onSurfaceCreated() {
        triangle = new Triangle();
        this.renderer.addGeometory(triangle);
    }

}
