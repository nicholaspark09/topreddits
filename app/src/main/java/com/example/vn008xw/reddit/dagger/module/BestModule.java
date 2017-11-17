package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.best.BestRedditsContract;
import com.example.vn008xw.reddit.ui.best.BestRedditsFragment;
import com.example.vn008xw.reddit.ui.best.BestRedditsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BestModule {

    @ContributesAndroidInjector
    abstract BestRedditsFragment bestFragment();

    @Binds
    public abstract BestRedditsContract.Presenter providesBestPresenter(BestRedditsPresenter bestRedditsPresenter);
}
