package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.base.BasePresenter;
import com.example.vn008xw.reddit.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * Handles the logic for the list of reddit entries
 */
public class BestRedditsPresenter
        extends BasePresenter<BestRedditsContract.View>
        implements BestRedditsContract.Presenter {

    // The code test stated pagination should be limited to 10 items
    private static final int LIMIT = 10;
    // The code test also stated this was to be a list of 50 entries from reddit
    private static final int UPPER_LIMIT = 50;

    @NonNull
    @VisibleForTesting
    final RedditRepository mRepository;
    @NonNull
    @VisibleForTesting
    final Scheduler mIoThread;
    @NonNull
    @VisibleForTesting
    final Scheduler mMainThread;
    @NonNull
    @VisibleForTesting
    String mMostRecentAfter = "";
    @VisibleForTesting
    boolean mIsLoading = false;

    @NonNull
    @VisibleForTesting
    final List<RedditDataChild> mPosts = new ArrayList<>();

    @Inject
    public BestRedditsPresenter(@NonNull RedditRepository redditRepository,
                                @NonNull @Named("IoThread") Scheduler ioThread,
                                @NonNull @Named("MainThread") Scheduler mainThread) {
        mRepository = redditRepository;
        mIoThread = ioThread;
        mMainThread = mainThread;
    }

    @Override
    public void getNextGroupOfPosts() {
        getPosts(mMostRecentAfter);
    }

    // For internal use
    @Override
    public void getPosts(@NonNull String after) {
        if (getView() != null && !mIsLoading && mPosts.size() < UPPER_LIMIT) {
            getView().showLoading(true);
            mIsLoading = true;
            final Disposable disposable = mRepository.getEntries(after, LIMIT)
                    .subscribeOn(mIoThread)
                    .observeOn(mMainThread)
                    .subscribe(
                            data -> {
                                getView().showLoading(false);
                                if (data != null) {
                                    mPosts.addAll(data.getChildren());
                                    getView().showEntries(mPosts);
                                    mMostRecentAfter = data.getAfter();
                                }
                                mIsLoading = false;
                            },
                            error -> {
                                getView().showLoading(false);
                                getView().showError("Couldn't get anything: " + error.getLocalizedMessage());
                                mIsLoading = false;
                            }
                    );
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void openImage(@NonNull RedditPost post, @NonNull ImageView imageView) {
        if (ImageUtil.hasImageUrl(post)) {
            getView().showImage(post, imageView);
        } else {
            getView().showNoImage();
        }
    }

    @Override
    public void openRedditDetail(@NonNull RedditPost redditPost,
                                 @NonNull TextView titleView,
                                 @NonNull TextView authorView,
                                 @Nullable ImageView imageView) {
        // Cache the detail
        mRepository.cachePost(redditPost);
        getView().showRedditDetail(ImageUtil.hasImageUrl(redditPost),
                redditPost.getId(),
                titleView,
                authorView,
                imageView);
    }

    @Override
    public void refresh() {
        mPosts.clear();
        mMostRecentAfter = "";
        mIsLoading = false;
        mRepository.refresh();
    }
}
