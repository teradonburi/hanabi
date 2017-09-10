package com.teradonburi.hanabi.inject;

import com.teradonburi.hanabi.MainApplication;
import com.teradonburi.hanabi.inject.lifecycle.AppCompatActivityModule;
import com.teradonburi.hanabi.inject.lifecycle.FragmentManagerModule;
import com.teradonburi.hanabi.inject.lifecycle.LifecycleComponent;
import com.teradonburi.hanabi.inject.game.GameModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daiki on 2017/09/03.
 */


@Component(modules = {MainModule.class})
@Singleton
public interface MainComponent {

    LifecycleComponent lifecycleComponent(
            AppCompatActivityModule appCompatActivityModule,
            FragmentManagerModule fragmentManagerModule,
            GameModule lifecycleModule
    );

    void inject(MainApplication mainApplication);
}
