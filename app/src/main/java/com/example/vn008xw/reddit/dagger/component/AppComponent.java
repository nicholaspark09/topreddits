package com.example.vn008xw.reddit.dagger.component;

import android.app.Application;

import com.example.vn008xw.reddit.RedditApplication;
import com.example.vn008xw.reddit.dagger.AppScope;
import com.example.vn008xw.reddit.dagger.module.ApiModule;
import com.example.vn008xw.reddit.dagger.module.AppModule;
import com.example.vn008xw.reddit.dagger.module.HomeActivityModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@AppScope
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        AndroidInjectionModule.class,
        HomeActivityModule.class
})
public interface AppComponent {


    void inject(RedditApplication applicationInstance);

    @Component.Builder
    interface Builder {

        AppComponent build();

        @BindsInstance
        Builder application(Application application);

        Builder apiModule(ApiModule apiModule);
    }
}