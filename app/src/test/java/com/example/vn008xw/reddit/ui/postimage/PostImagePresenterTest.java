package com.example.vn008xw.reddit.ui.postimage;


import android.graphics.Bitmap;

import com.example.vn008xw.reddit.data.image.ImageRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class PostImagePresenterTest {

    private static final String POST_ID = "19292";

    @Mock private ImageRepository imageRepository;
    @Mock private PostImageContract.View view;
    @Mock private Bitmap bitmap;
    private Scheduler thread;
    private Scheduler mainThread;
    private PostImagePresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        thread = Schedulers.trampoline();
        mainThread = Schedulers.trampoline();
        presenter = new PostImagePresenter(imageRepository, thread, mainThread);
        presenter.attachView(view);
        presenter.mCompositeDisposable = new CompositeDisposable();
    }

    @Test
    public void observeSavedState() {
        when(imageRepository.getSavedStatus()).thenReturn(Observable.just(true));
        presenter.observeSavedStatus();
        verify(view).showSavedStatus(true);
    }

    @Test
    public void start_setId() {
        when(imageRepository.getSavedStatus()).thenReturn(Observable.just(true));
        presenter.start(POST_ID);
        verify(imageRepository).setPostId(POST_ID);
    }

    @Test
    public void saveImage_getUrl() {
        when(imageRepository.saveImage(bitmap)).thenReturn(Observable.just(""));
        presenter.saveImage(bitmap);
        verify(imageRepository).saveImage(bitmap);
    }

    @Test
    public void deleteImage_deletedImage() {
        presenter.deleteImage();
        verify(imageRepository).deleteImage();
    }

    // Test exceptions and errors
    @Test
    public void saveImage_getError() {
        when(imageRepository.saveImage(bitmap))
                .thenReturn(Observable.error(new Exception("")));
        presenter.saveImage(bitmap);
        verify(view).showImageNotSaved();
    }
}
