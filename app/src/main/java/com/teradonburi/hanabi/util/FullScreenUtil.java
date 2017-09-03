package com.teradonburi.hanabi.util;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/03.
 */

@Lifecycle
public class FullScreenUtil {

    private final AppCompatActivity activity;

    @Inject
    public FullScreenUtil(AppCompatActivity activity){
        this.activity = activity;
    }

    public void setFullScreen(){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = activity.getWindow();
            View view = window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }
}
