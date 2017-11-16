package com.example.vn008xw.reddit.data.reddit;


import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.dagger.Remote;
import com.example.vn008xw.reddit.data.vo.RedditData;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RedditRepository implements RedditDataSource {

    @NonNull
    private final RedditDataSource mRemoteSource;
    @NonNull
    private final HashMap<String, RedditData> mCache = new HashMap<>();
    private boolean mCacheIsDirty = false;

    @Inject
    public RedditRepository(@Remote RedditDataSource dataSource) {
        mRemoteSource = dataSource;
    }

    @Override
    public Observable<RedditData> getEntries(@NonNull String after, int limit) {
        if (!mCacheIsDirty && mCache.containsKey(after)) {
            return Observable.just(mCache.get(after));
        }
        return mRemoteSource.getEntries(after, limit)
                .doOnNext(redditData -> {
                    if (redditData != null) {
                        mCache.put(redditData.getAfter(), redditData);
                        mCacheIsDirty = false;
                    }
                });
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }
}