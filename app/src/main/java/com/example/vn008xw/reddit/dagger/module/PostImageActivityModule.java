package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.ui.postimage.PostImageActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PostImageActivityModule {

    @ContributesAndroidInjector(modules = {PostImageModule.class})
    abstract PostImageActivity postImageActivity();
}
