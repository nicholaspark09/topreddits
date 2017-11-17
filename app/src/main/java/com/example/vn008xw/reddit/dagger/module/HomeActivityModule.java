package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.home.HomeActivity;
import com.example.vn008xw.reddit.ui.postimage.PostImageActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeActivityModule {

    @ContributesAndroidInjector(modules = {BestModule.class})
    abstract HomeActivity contributeHomeActivity();
}