package com.example.vn008xw.reddit.data.reddit;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.api.RedditService;
import com.example.vn008xw.reddit.data.vo.RedditData;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RedditRemoteSource implements RedditDataSource {

    @NonNull
    private final RedditService mRedditService;

    @Inject
    public RedditRemoteSource(@NonNull RedditService redditService) {
        mRedditService = redditService;
    }

    @Override
    public Observable<RedditData> getEntries(@NonNull String after, int limit) {
        return mRedditService.getPosts(after, limit)
                .flatMap(response ->
                        Observable.just(response.getRedditData())
                );
    }

    @Override
    public void refresh() {
        throw new AssertionError("Not implemented in remote source");
    }
}
