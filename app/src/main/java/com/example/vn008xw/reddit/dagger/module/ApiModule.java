package com.example.vn008xw.reddit.dagger.module;

import android.app.Application;

import com.example.vn008xw.reddit.BuildConfig;
import com.example.vn008xw.reddit.dagger.AppScope;
import com.example.vn008xw.reddit.data.api.RedditService;
import com.example.vn008xw.reddit.util.DaggerUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

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
    @AppScope
    Gson provideGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        return DaggerUtil.track(gsonBuilder.create());
    }

    @Provides
    @AppScope
    Cache provideCache(Application application) {
        return DaggerUtil.track(new Cache(application.getCacheDir(), DISK_CACHE_SIZE));
    }

    @Provides
    @AppScope
    OkHttpClient provideOkHttpClient(Cache cache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        return DaggerUtil.track(builder.build());
    }

    @Provides
    @AppScope
    @Named("Endpoint")
    String provideBaseUrl() {
        return BuildConfig.ENDPOINT;
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(Gson gson,
                             OkHttpClient okHttpClient,
                             @Named("Endpoint") String endPoint) {
        return DaggerUtil.track(
                new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(endPoint)
                .client(okHttpClient)
                        .build()
        );
    }

    @Provides
    @AppScope
    RedditService provideRedditService(Retrofit retrofit) {
        return DaggerUtil.track(retrofit.create(RedditService.class));
    }
}