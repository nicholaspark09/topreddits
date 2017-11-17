package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.ui.base.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

public class BestPresenter
        extends BasePresenter<BestContract.View>
        implements BestContract.Presenter {

    // The code test stated pagination should be limited to 10 items
    private static final int LIMIT = 10;

    @NonNull
    private final RedditRepository mRepository;
    @NonNull
    private final Scheduler mIoThread;
    @NonNull
    private final Scheduler mMainThread;

    @Inject
    public BestPresenter(@NonNull RedditRepository redditRepository,
                         @NonNull @Named("IoThread") Scheduler ioThread,
                         @NonNull @Named("MainThread") Scheduler mainThread) {
        mRepository = redditRepository;
        mIoThread = ioThread;
        mMainThread = mainThread;
    }

    @Override
    public void getPosts(@NonNull String after) {
        if (getView() != null) {
            getView().showLoading(true);
            mRepository.getEntries(after, LIMIT)
                    .subscribeOn(mIoThread)
                    .observeOn(mMainThread)
                    .subscribe(
                            data -> {
                                getView().showLoading(false);
                                if (data != null) {
                                    getView().showEntries(data.getChildren());
                                }
                            },
                            error -> {
                                getView().showLoading(false);
                                getView().showError("Couldn't get anything: "+ error.getLocalizedMessage());
                            }
                    );
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void openImage(@NonNull String thumbnail) {

    }
}
