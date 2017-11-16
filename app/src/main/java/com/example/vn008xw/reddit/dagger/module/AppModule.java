package com.example.vn008xw.reddit.dagger.module;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.vn008xw.reddit.dagger.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @AppScope
    public RequestManager provideRequestManager(Application application) {
        return Glide.with(application);
    }
}
