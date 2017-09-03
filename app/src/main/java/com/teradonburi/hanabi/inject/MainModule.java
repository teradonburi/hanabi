package com.teradonburi.hanabi.inject;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daiki on 2017/09/03.
 */

@Module
@Singleton
public class MainModule {

    private Context appContext;

    public MainModule(Context appContext) {
        this.appContext = appContext;
    }


    @Provides
    @Singleton
    @ForApplication
    public Context provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreference() {
        return appContext.getSharedPreferences(appContext.getPackageName(),Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }

}
