package com.example.vn008xw.reddit.data.reddit;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.vo.RedditData;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;

import io.reactivex.Observable;

public interface RedditDataSource {

    Observable<RedditData> getEntries(@NonNull String after,
                                      int limit);

    void refresh();
}