package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.base.BasePresenterContract;
import com.example.vn008xw.reddit.ui.base.BaseView;

import java.util.List;

public interface BestRedditsContract {

    interface View extends BaseView {
        void showEntries(@NonNull List<RedditDataChild> entries);

        void showImage(@NonNull RedditPost post, @NonNull ImageView imageView);

        void showNoImage();

        void showRedditDetail(boolean hasImage,
                              @NonNull String postId,
                              @NonNull TextView titleView,
                              @NonNull TextView authorView,
                              @Nullable ImageView imageView);
    }

    interface Presenter extends BasePresenterContract<View> {
        void getNextGroupOfPosts();

        void getPosts(@NonNull String after);

        void openImage(@NonNull RedditPost post,
                       @NonNull ImageView imageView);

        void openRedditDetail(@NonNull RedditPost redditPost,
                              @NonNull TextView titleView,
                              @NonNull TextView authorView,
                              @Nullable ImageView imageView);

        void refresh();
    }
}