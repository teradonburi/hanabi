package com.teradonburi.hanabi.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.teradonburi.hanabi.R;
import com.teradonburi.hanabi.fragment.MainFragment;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;
import com.teradonburi.hanabi.util.FullScreenUtil;
import com.teradonburi.hanabi.util.TransitionUtil;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class MainActivityViewModel extends BaseObservable {

    private final TransitionUtil transitionUtil;

    @Inject
    public MainActivityViewModel(FullScreenUtil fullScreenUtil, TransitionUtil transitionUtil){
        this.transitionUtil = transitionUtil;
        fullScreenUtil.setFullScreen();
    }

    public void transiton(Bundle savedInstanceState){
        if(savedInstanceState == null){
            transitionUtil.replace(R.id.container,new MainFragment());
        }
    }

}
