package com.example.vn008xw.reddit.dagger.module;

import com.example.vn008xw.reddit.dagger.AppScope;
import com.example.vn008xw.reddit.dagger.Remote;
import com.example.vn008xw.reddit.data.api.RedditService;
import com.example.vn008xw.reddit.data.reddit.RedditDataSource;
import com.example.vn008xw.reddit.data.reddit.RedditRemoteSource;
import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.util.DaggerUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class RedditModule {

    @Provides
    @AppScope
    @Remote
    RedditDataSource provideRemoteRedditSource(RedditService redditService) {
        return DaggerUtil.track(new RedditRemoteSource(redditService));
    }

    @Provides
    @AppScope
    RedditRepository provideRedditRepository(@Remote RedditDataSource redditDataSource) {
        return DaggerUtil.track(new RedditRepository(redditDataSource));
    }
}
