package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.ui.base.BasePresenterContract;
import com.example.vn008xw.reddit.ui.base.BaseView;

import java.util.List;

public interface BestRedditsContract {

    interface View extends BaseView {
        void showEntries(@NonNull List<RedditDataChild> entries);

        void showImage(@NonNull String thumbnail, @NonNull ImageView imageView);
    }

    interface Presenter extends BasePresenterContract<View> {
        void getNextGroupOfPosts();
        void getPosts(@NonNull String after);
        void refresh();
    }
}