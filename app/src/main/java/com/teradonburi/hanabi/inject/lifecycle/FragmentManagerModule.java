package com.teradonburi.hanabi.inject.lifecycle;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daiki on 2017/09/03.
 */


@Module
public class FragmentManagerModule {

    private FragmentManagerDelegate delegate;

    public FragmentManagerModule(FragmentManagerDelegate delegate) {
        this.delegate = delegate;
    }

    @Lifecycle
    @ForActivity
    @Provides
    FragmentManager activityFragmentManager() {
        return delegate.activityFragmentManager();
    }

    @Lifecycle
    @Provides
    FragmentManager currentFragmentManager() {
        return delegate.currentFragmentManager();
    }
}

