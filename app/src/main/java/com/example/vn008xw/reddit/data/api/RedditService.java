package com.example.vn008xw.reddit.data.api;

import com.example.vn008xw.reddit.data.vo.RedditResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditService {

    @GET("top.json")
    Observable<RedditResponse> getPosts(@Query("after") String after,
                                        @Query("limit") int limit);
}