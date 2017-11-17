package com.example.vn008xw.reddit.ui.postimage;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.image.ImageRepository;
import com.example.vn008xw.reddit.ui.base.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class PostImagePresenter
        extends BasePresenter<PostImageContract.View>
        implements PostImageContract.Presenter {

    @NonNull
    private final ImageRepository mRepository;
    @NonNull
    private final Scheduler mIoThread;
    @NonNull
    private final Scheduler mMainThread;

    @Inject
    public PostImagePresenter(@NonNull ImageRepository imageRepository,
                              @NonNull @Named("IoThread") Scheduler ioThread,
                              @NonNull @Named("MainThread") Scheduler mainThread) {
        mRepository = imageRepository;
        mIoThread = ioThread;
        mMainThread = mainThread;
    }

    @Override
    public void saveImage(@NonNull String url, @NonNull Bitmap bitmap) {
        final Disposable disposable =
                mRepository.saveImage(url, bitmap)
                        .subscribeOn(mIoThread)
                        .observeOn(mMainThread)
                        .subscribe(
                                result -> {
                                    getView().showSaveSuccess(result);
                                },
                                error -> {
                                    getView().showError("Sorry, no luck. Please try again.");
                                }
                        );
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsaveImage(@NonNull String url) {

    }
}
