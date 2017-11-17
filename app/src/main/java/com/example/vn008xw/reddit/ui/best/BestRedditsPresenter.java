package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

public class BestRedditsPresenter
        extends BasePresenter<BestRedditsContract.View>
        implements BestRedditsContract.Presenter {

    // The code test stated pagination should be limited to 10 items
    private static final int LIMIT = 10;
    private static final int UPPER_LIMIT = 50;

    @NonNull
    private final RedditRepository mRepository;
    @NonNull
    private final Scheduler mIoThread;
    @NonNull
    private final Scheduler mMainThread;
    @NonNull
    private String mMostRecentAfter = "";
    private boolean mIsLoading = false;
    private final List<RedditDataChild> mPosts = new ArrayList<>();

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
            mRepository.getEntries(after, LIMIT)
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
        }
    }

    @Override
    public void refresh() {
        mPosts.clear();
        mMostRecentAfter = "";
        mIsLoading = false;
        mRepository.refresh();
        getNextGroupOfPosts();
    }

    @Override
    public void openImage(@NonNull String thumbnail) {

    }
}
