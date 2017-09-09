package com.teradonburi.hanabi.game;

import android.opengl.GLSurfaceView;

import com.teradonburi.hanabi.entity.UserEntity;
import com.teradonburi.hanabi.game.geometory.Triangle;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class GameMain implements GameRendererEvent{

    private final SaveData saveData;
    private final GameRenderer renderer;
    private final Triangle triangle;
    private UserEntity userEntity;


    @Inject
    public GameMain(final SaveData saveData,
                    final GameRenderer renderer,
                    final Triangle triangle){
        this.saveData = saveData;
        this.renderer = renderer;
        this.triangle = triangle;
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

    // サーフェスの初期化処理
    @Override
    public void onGameInit() {
        triangle.shaderLoad();
        this.renderer.addGeometory(triangle);
    }

    @Override
    public void onGameLoop() {

    }


}
