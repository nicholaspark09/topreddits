package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.best.BestContract;
import com.example.vn008xw.reddit.ui.best.BestFragment;
import com.example.vn008xw.reddit.ui.best.BestPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BestModule {

    @ContributesAndroidInjector
    abstract BestFragment bestFragment();

    @Binds
    public abstract BestContract.Presenter providesBestPresenter(BestPresenter bestPresenter);
}
