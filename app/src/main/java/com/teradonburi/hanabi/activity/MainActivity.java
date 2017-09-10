package com.teradonburi.hanabi.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.teradonburi.hanabi.R;
import com.teradonburi.hanabi.databinding.ActivityMainBinding;
import com.teradonburi.hanabi.inject.lifecycle.LifecycleComponent;
import com.teradonburi.hanabi.viewmodel.MainActivityViewModel;

public class MainActivity extends LifecycleActivity {

    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel.transiton(savedInstanceState);
    }

    @Override
    protected void prepare(LifecycleComponent component) {
        viewModel = component.mainActivityViewModel();
    }

}
