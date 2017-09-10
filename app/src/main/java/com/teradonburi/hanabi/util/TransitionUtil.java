package com.teradonburi.hanabi.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.inject.lifecycle.ForActivity;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class TransitionUtil {

    private final AppCompatActivity activity;
    private final FragmentManager activityFragmentManager;
    private final FragmentManager currentFragmentManager;

    @Inject
    public TransitionUtil(AppCompatActivity activity,
                          @ForActivity FragmentManager activityFragmentManager,
                          FragmentManager currentFragmentManager){
        this.activity = activity;
        this.activityFragmentManager = activityFragmentManager;
        this.currentFragmentManager = currentFragmentManager;
    }

    public void startActivity(Intent intent){
        activity.startActivity(intent);
    }

    public void startActivity(Intent intent, Bundle data){
        activity.startActivity(intent,data);
    }

    public void replace(@IdRes int layoutId, Fragment fragment){
        activityFragmentManager.beginTransaction()
                .replace(layoutId,fragment)
                .commit();
    }

    public void replaceChild(@IdRes int layoutId, Fragment fragment){
        currentFragmentManager.beginTransaction()
                .replace(layoutId,fragment)
                .commit();
    }

}
