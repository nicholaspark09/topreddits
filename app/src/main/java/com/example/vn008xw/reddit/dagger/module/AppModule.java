package com.example.vn008xw.reddit.dagger.module;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.vn008xw.reddit.dagger.AppScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {

    @Provides
    @AppScope
    public RequestManager provideRequestManager(Application application) {
        return Glide.with(application);
    }

    @Provides
    @AppScope
    @Named("IoThread")
    Scheduler provideIoThread() {
        return Schedulers.io();
    }

    @Provides
    @AppScope
    @Named("MainThread")
    Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @AppScope
    @Named("AppName")
    String provideAppName(Application application) {
        return "Redditer";
    }
}
