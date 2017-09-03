package com.teradonburi.hanabi.inject.lifecycle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by daiki on 2017/09/03.
 */

public class FragmentManagerDelegateForFragment implements FragmentManagerDelegate {

    private Fragment fragment;

    public FragmentManagerDelegateForFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public FragmentManager activityFragmentManager() {
        return fragment.getActivity().getSupportFragmentManager();
    }

    @Override
    public FragmentManager currentFragmentManager() {
        return fragment.getChildFragmentManager();
    }
}
