package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.dagger.AppScope;
import com.example.vn008xw.reddit.data.image.ImageRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    @AppScope
    ImageRepository provideImageRepository(@Named("AppName") String appName) {
        return new ImageRepository(appName);
    }
}
