package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.postimage.PostImageContract;
import com.example.vn008xw.reddit.ui.postimage.PostImagePresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PostImageModule {

    @Binds
    public abstract PostImageContract.Presenter providePresenter(PostImagePresenter presenter);
}
