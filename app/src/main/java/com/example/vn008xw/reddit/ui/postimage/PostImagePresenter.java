package com.example.vn008xw.reddit.ui.postimage;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.image.ImageRepository;
import com.example.vn008xw.reddit.ui.base.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

// Handles logic for the PostImage screen which has a full screen image of the
// reddit post's thumbnail
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
    public void start(@NonNull String id) {
        mRepository.setPostId(id);
        observeSavedStatus();
    }

    @Override
    public void observeSavedStatus() {
        final Disposable disposable =
                mRepository.getSavedStatus()
                        .subscribeOn(mIoThread)
                        .observeOn(mMainThread)
                        .subscribe(
                                isSaved ->
                                        getView().showSavedStatus(isSaved),
                                error ->
                                        Timber.e(error, "Exception: " + error.getLocalizedMessage())
                        );
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void saveImage(@NonNull Bitmap bitmap) {
        final Disposable disposable =
                mRepository.saveImage(bitmap)
                        .subscribeOn(mIoThread)
                        .observeOn(mMainThread)
                        .subscribe(
                                urlResult -> {
                                    // The behaviorsubject should be updated so this is only
                                    // if you wanted to update the users more strongly
                                    Timber.d("Saved the image with path: " + urlResult);
                                },
                                error -> {
                                    Timber.e("Exception: " + error.getLocalizedMessage());
                                    getView().showError("Sorry, no luck. Please try again.");
                                }
                        );
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void deleteImage() {
        mRepository.deleteImage();
    }
}