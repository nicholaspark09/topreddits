package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.redditpost.RedditPostDetailContract;
import com.example.vn008xw.reddit.ui.redditpost.RedditPostDetailPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RedditPostModule {

    @Binds
    public abstract RedditPostDetailContract.Presenter providePresenter(
            RedditPostDetailPresenter presenter);
}