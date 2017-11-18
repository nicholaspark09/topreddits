package com.example.vn008xw.reddit.ui.redditpost;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.base.BasePresenterContract;
import com.example.vn008xw.reddit.ui.base.BaseView;

public interface RedditPostDetailContract {

    interface View extends BaseView {
        void showPostDetails(@NonNull RedditPost post);

        void showThumbnailImage(@NonNull RedditPost post);

        void showLargeImage(@NonNull String imageUrl);

        void showVideoIsYoutube();

        void showYoutubeVideo(@NonNull String url);

        void showNoImage();

        void showNoPostError();
    }

    interface Presenter extends BasePresenterContract<View> {
        void findPost(@NonNull String postId);

        void openLargeImage(@NonNull String postId);

        void openYoutubeVideo(@NonNull String postId);
    }
}
