package com.teradonburi.hanabi.inject.lifecycle;

import com.teradonburi.hanabi.inject.game.GameModule;
import com.teradonburi.hanabi.viewmodel.MainActivityViewModel;
import com.teradonburi.hanabi.viewmodel.MainFragmentViewModel;

import dagger.Subcomponent;

/**
 * Created by daiki on 2017/09/03.
 */

@Subcomponent(modules = {AppCompatActivityModule.class,FragmentManagerModule.class,GameModule.class})
@Lifecycle
public interface LifecycleComponent {

    MainActivityViewModel mainActivityViewModel();

    MainFragmentViewModel mainFragmentViewModel();
}
