package com.teradonburi.hanabi;

import android.app.Application;

import com.teradonburi.hanabi.inject.DaggerMainComponent;
import com.teradonburi.hanabi.inject.MainComponent;
import com.teradonburi.hanabi.inject.MainModule;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

public class MainApplication extends Application {

    private static MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMainComponent.builder()
                .mainModule(new MainModule(getApplicationContext()))
                .build();
    }

    public static MainComponent getComponent(){
        return component;
    }

}
