package com.teradonburi.hanabi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.MainApplication;
import com.teradonburi.hanabi.inject.lifecycle.AppCompatActivityModule;
import com.teradonburi.hanabi.inject.lifecycle.FragmentManagerDelegateForActivity;
import com.teradonburi.hanabi.inject.lifecycle.FragmentManagerDelegateForFragment;
import com.teradonburi.hanabi.inject.lifecycle.FragmentManagerModule;
import com.teradonburi.hanabi.inject.game.GameModule;
import com.teradonburi.hanabi.inject.lifecycle.LifecycleComponent;

/**
 * Created by daiki on 2017/09/03.
 */

public abstract class LifecycleFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LifecycleComponent component = MainApplication.getComponent()
                .lifecycleComponent(
                        new AppCompatActivityModule((AppCompatActivity) getActivity()),
                        new FragmentManagerModule(new FragmentManagerDelegateForFragment(this)),
                        new GameModule());
        prepare(component);
    }

    protected abstract void prepare(LifecycleComponent component);
}
