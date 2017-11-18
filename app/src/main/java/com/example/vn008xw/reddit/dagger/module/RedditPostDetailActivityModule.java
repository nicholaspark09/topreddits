package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.redditpost.RedditPostDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RedditPostDetailActivityModule {

    @ContributesAndroidInjector(modules = {RedditPostModule.class})
    abstract RedditPostDetailActivity redditPostDetailActivity();
}
