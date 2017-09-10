package com.teradonburi.hanabi.inject.lifecycle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by daiki on 2017/09/03.
 */

public class FragmentManagerDelegateForActivity implements FragmentManagerDelegate {

    private FragmentActivity activity;

    public FragmentManagerDelegateForActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public FragmentManager activityFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Override
    public FragmentManager currentFragmentManager() {
        return activity.getSupportFragmentManager();
    }
}
