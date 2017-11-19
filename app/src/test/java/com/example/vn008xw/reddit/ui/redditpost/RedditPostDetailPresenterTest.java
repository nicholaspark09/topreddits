package com.example.vn008xw.reddit.ui.redditpost;

import com.example.vn008xw.reddit.data.reddit.RedditRepository;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RedditPostDetailPresenterTest {

    private static final String ID = "1999";
    private static final RedditPost IMAGE_POST = TestUtil.createRedditPost(ID);
    private static final RedditPost YOUTUBE_POST = TestUtil.createVideoPost(ID);

    @Mock private RedditRepository redditRepository;
    @Mock private RedditPostDetailContract.View view;
    private RedditPostDetailPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new RedditPostDetailPresenter(redditRepository);
        presenter.attachView(view);
    }

    @Test
    public void findPost_noPost_showError() {
        when(redditRepository.getCachedPost(ID)).thenReturn(null);
        presenter.findPost(ID);
        verify(view).showNoPostError();
    }

    @Test
    public void findPost_showThumnailImage() {
        when(redditRepository.getCachedPost(ID)).thenReturn(IMAGE_POST);
        presenter.findPost(ID);
        verify(view).showThumbnailImage(IMAGE_POST);
    }

    @Test
    public void findPost_showVideoIsYoutube() {
        when(redditRepository.getCachedPost(ID)).thenReturn(YOUTUBE_POST);
        presenter.findPost(ID);
        verify(view).showVideoIsYoutube();
    }

    @Test
    public void findPost_showNoImage() {
        final RedditPost post = TestUtil.createRedditPost(ID);
        post.setThumbnail("");
        post.setUrl("");
        when(redditRepository.getCachedPost(ID)).thenReturn(post);
        presenter.findPost(ID);
        verify(view).showNoImage();
    }

    @Test
    public void openLargeImage_showLargeImage() {
        when(redditRepository.getCachedPost(ID)).thenReturn(IMAGE_POST);
        presenter.openLargeImage(ID);
        verify(view).showLargeImage(IMAGE_POST.getUrl());
    }

    @Test
    public void openYoutube_showVideo() {
        when(redditRepository.getCachedPost(ID)).thenReturn(YOUTUBE_POST);
        presenter.openYoutubeVideo(ID);
        verify(view).showYoutubeVideo(YOUTUBE_POST.getUrl());
    }
}
