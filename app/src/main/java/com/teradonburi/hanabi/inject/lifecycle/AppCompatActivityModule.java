package com.teradonburi.hanabi.inject.lifecycle;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daiki on 2017/09/03.
 */

@Module
public class AppCompatActivityModule {

    private AppCompatActivity activity;

    public AppCompatActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Lifecycle
    @Provides
    AppCompatActivity appCompatActivity() {
        return activity;
    }
}
