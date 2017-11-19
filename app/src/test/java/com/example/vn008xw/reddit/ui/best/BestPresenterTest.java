package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.data.vo.RedditData;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BestPresenterTest {

    private static final String AFTER_KEY = "12w233";

    @Mock private BestRedditsContract.View view;
    @Mock private RedditRepository redditRepository;
    private Scheduler ioThread;
    private Scheduler mainThread;

    private BestRedditsPresenter presenter;

    private static final RedditData REDDIT_DATA = TestUtil.createMockRedditData(AFTER_KEY);

    @Before
    public void setupPresenterTest() {
        MockitoAnnotations.initMocks(this);

        ioThread = Schedulers.trampoline();
        mainThread = Schedulers.trampoline();
        presenter = new BestRedditsPresenter(redditRepository, ioThread, mainThread);
        presenter.mCompositeDisposable = new CompositeDisposable();
        presenter.attachView(view);
    }

    @Test
    public void getPosts_newPosts() {
        final String afterKey = "";
        setupEntryResponses(afterKey);
        presenter.getPosts(afterKey);
        verify(redditRepository).getEntries(afterKey, 10);
        assertEquals(REDDIT_DATA.getChildren().size(), presenter.mPosts.size());
        assertEquals(AFTER_KEY, presenter.mMostRecentAfter);
    }

    @Test
    public void openImage_NoImage_showNoImage() {
        final RedditPost post = TestUtil.createRedditPost("1232");
        post.setThumbnail("");
        presenter.openImage(post, mock(ImageView.class));
        verify(view).showNoImage();
    }

    @Test
    public void openImageWithUrl_showImage() {
        final ImageView imageView = mock(ImageView.class);
        final RedditPost post = TestUtil.createRedditPost("111");
        presenter.openImage(post, imageView);
        verify(view).showImage(post, imageView);
    }

    @Test
    public void openRedditDetail_cacheInRepository_showRedditDetail() {
        final RedditPost post = TestUtil.createRedditPost("111");
        final ImageView imageView = mock(ImageView.class);
        final TextView titleView = mock(TextView.class);
        final TextView authorView = mock(TextView.class);
        presenter.openRedditDetail(post, titleView, authorView, imageView);
        verify(redditRepository).cachePost(post);
        verify(view).showRedditDetail(true, post.getId(),
                titleView, authorView, imageView);
    }

    @Test
    public void refresh_clearErrthing() {
        final String afterKey = "";
        presenter.mMostRecentAfter = "1234";
        setupEntryResponses(afterKey);
        presenter.refresh();
        verify(redditRepository).refresh();
        assertEquals(0, presenter.mPosts.size());
        assertEquals("", presenter.mMostRecentAfter);
    }

    @Test
    public void getPosts_exception_showMessage() {
        when(redditRepository.getEntries(AFTER_KEY, 10))
                .thenReturn(Observable.error(new Exception("Ruh roh")));
        presenter.getPosts(AFTER_KEY);
        verify(view).showError("Couldn't get anything: Ruh roh");
    }

    @Test
    public void getNextGroupOfPosts_searchesMostRecentAfterKey() {
        final String TEST_KEY = "101010";
        presenter.mMostRecentAfter = TEST_KEY;
        setupEntryResponses(TEST_KEY);
        presenter.getNextGroupOfPosts();
        verify(redditRepository).getEntries(TEST_KEY, 10);
    }

    private void setupEntryResponses(@NonNull String afterKey) {
        when(redditRepository.getEntries(afterKey, 10))
                .thenReturn(Observable.just(REDDIT_DATA));
    }
}
