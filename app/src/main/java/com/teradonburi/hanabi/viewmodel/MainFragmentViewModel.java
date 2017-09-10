package com.teradonburi.hanabi.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.opengl.GLSurfaceView;

import com.teradonburi.hanabi.game.GameMain;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class MainFragmentViewModel extends BaseObservable{

    private final GameMain gameMain;

    @Inject
    public MainFragmentViewModel(GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Bindable
    public GLSurfaceView.Renderer getRender(){
        return gameMain.getRenderer();
    }

}
