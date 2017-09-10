package com.teradonburi.hanabi.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teradonburi.hanabi.R;
import com.teradonburi.hanabi.databinding.FragmentMainBinding;
import com.teradonburi.hanabi.inject.lifecycle.LifecycleComponent;
import com.teradonburi.hanabi.viewmodel.MainFragmentViewModel;

/**
 * Created by daiki on 2017/09/03.
 */

public class MainFragment extends LifecycleFragment {

    private MainFragmentViewModel viewModel;
    private FragmentMainBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.surfaceView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.surfaceView.onPause();
    }

    @Override
    protected void prepare(LifecycleComponent component) {
        viewModel = component.mainFragmentViewModel();
    }
}
