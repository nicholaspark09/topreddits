package com.example.vn008xw.reddit.ui.redditpost;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.base.BasePresenter;
import com.example.vn008xw.reddit.util.ImageUtil;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

public class RedditPostDetailPresenter
        extends BasePresenter<RedditPostDetailContract.View>
        implements RedditPostDetailContract.Presenter {

    @NonNull
    private final RedditRepository mRedditRepository;

    @Inject
    public RedditPostDetailPresenter(@NonNull RedditRepository redditRepository) {
        mRedditRepository = redditRepository;
    }

    @Override
    public void findPost(@NonNull String postId) {
        final RedditPost post = mRedditRepository.getCachedPost(postId);
        if (post == null) {
            getView().showNoPostError();
        } else {
            getView().showPostDetails(post);
            if (ImageUtil.hasImageUrl(post)) {
                getView().showThumbnailImage(post);
            } else {
                getView().showNoImage();
            }
            if (ImageUtil.isYoutubeVideo(post.getUrl())) {
                getView().showVideoIsYoutube();
            }
        }
    }

    @Override
    public void openLargeImage(@NonNull String postId) {
        final RedditPost post = mRedditRepository.getCachedPost(postId);
        if (post != null && ImageUtil.hasLargeImage(post.getUrl())) {
            getView().showLargeImage(post.getUrl());
        }
    }

    @Override
    public void openYoutubeVideo(@NonNull String postId) {
        final RedditPost post = mRedditRepository.getCachedPost(postId);
        if (post != null && ImageUtil.isYoutubeVideo(post.getUrl())) {
            getView().showYoutubeVideo(post.getUrl());
        }
    }
}
