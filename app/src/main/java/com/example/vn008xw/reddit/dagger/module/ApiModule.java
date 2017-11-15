package com.example.vn008xw.reddit.dagger.module;

import android.app.Application;

import com.example.vn008xw.reddit.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class ApiModule {

    private static final long DISK_CACHE_SIZE = 20 * 1024 * 1024;

    @Provides
    @Singleton
    Gson provideGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), DISK_CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        return builder
                .build();
    }

    @Provides
    @Singleton
    @Named("Endpoint")
    String provideBaseUrl() {
        return BuildConfig.ENDPOINT;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson,
                             OkHttpClient okHttpClient,
                             @Named("Endpoint") String endPoint) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(endPoint)
                .client(okHttpClient)
                .build();
    }
}