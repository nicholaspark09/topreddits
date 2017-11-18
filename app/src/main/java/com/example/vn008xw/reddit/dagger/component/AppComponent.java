package com.example.vn008xw.reddit.dagger.component;

import android.app.Application;

import com.example.vn008xw.reddit.RedditApplication;
import com.example.vn008xw.reddit.dagger.AppScope;
import com.example.vn008xw.reddit.dagger.module.ApiModule;
import com.example.vn008xw.reddit.dagger.module.AppModule;
import com.example.vn008xw.reddit.dagger.module.HomeActivityModule;
import com.example.vn008xw.reddit.dagger.module.ImageModule;
import com.example.vn008xw.reddit.dagger.module.PostImageActivityModule;
import com.example.vn008xw.reddit.dagger.module.RedditModule;
import com.example.vn008xw.reddit.dagger.module.RedditPostDetailActivityModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        ImageModule.class,
        AndroidSupportInjectionModule.class,
        RedditModule.class,
        HomeActivityModule.class,
        PostImageActivityModule.class,
        RedditPostDetailActivityModule.class
})
public interface AppComponent {

    void inject(RedditApplication applicationInstance);

    @Component.Builder
    interface Builder {

        AppComponent build();

        @BindsInstance
        Builder application(Application application);

        // To enable a different api module to be used for testing or mocking
        Builder apiModule(ApiModule apiModule);
    }
}