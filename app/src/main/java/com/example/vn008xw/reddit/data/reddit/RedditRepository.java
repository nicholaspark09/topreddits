package com.example.vn008xw.reddit.data.reddit;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.example.vn008xw.reddit.dagger.Remote;
import com.example.vn008xw.reddit.data.vo.RedditData;
import com.example.vn008xw.reddit.data.vo.RedditPost;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RedditRepository implements RedditDataSource {

    @NonNull
    @VisibleForTesting
    final RedditDataSource mRemoteSource;

    @NonNull
    @VisibleForTesting
    final HashMap<String, RedditData> mCache = new HashMap<>();

    @Nullable
    @VisibleForTesting
    RedditPost mCachedPost;

    @VisibleForTesting boolean mCacheIsDirty = false;

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
                        mCache.put(after, redditData);
                        mCacheIsDirty = false;
                    }
                });
    }

    @Override
    public void cachePost(@NonNull RedditPost post) {
        mCachedPost = post;
    }

    @Nullable
    @Override
    public RedditPost getCachedPost(@NonNull String postId) {
        return mCachedPost != null && mCachedPost.getId().equals(postId) ? mCachedPost : null;
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }
}