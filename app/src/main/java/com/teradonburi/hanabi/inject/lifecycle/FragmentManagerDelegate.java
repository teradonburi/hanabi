package com.teradonburi.hanabi.inject.lifecycle;

/**
 * Created by daiki on 2017/09/03.
 */

import android.support.v4.app.FragmentManager;

public interface FragmentManagerDelegate {

    FragmentManager activityFragmentManager();

    FragmentManager currentFragmentManager();
}
