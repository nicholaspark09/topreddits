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
    public void getTopFifty() {
        if (getView() != null) {
            getView().showLoading(true);
            mRepository.getAllEntries()
                    .subscribeOn(mIoThread)
                    .observeOn(mMainThread)
                    .subscribe(
                            data -> {
                                Log.d("Presenter", "You got data back: " +data.toString()+" size: " + data.size());
                                for (RedditDataChild child : data) {
                                    Log.d("Presenter", "Child: " + child.getRedditPost().toString());
                                }
                            },
                            error -> {
                                getView().showError("Couldn't get anything");
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
