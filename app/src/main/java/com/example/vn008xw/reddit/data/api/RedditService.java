package com.example.vn008xw.reddit.data.api;

import com.example.vn008xw.reddit.dagger.AppScope;
import com.example.vn008xw.reddit.data.vo.EntryFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

@AppScope
public interface RedditService {

    @GET("top.json")
    Observable<EntryFeed> getTopPosts(@Query("count") int count);
}
