package com.example.vn008xw.reddit.data.reddit;


import com.example.vn008xw.reddit.data.vo.RedditData;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.ui.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class RedditRepositoryTest {

    private static final int LIMIT = 10;
    private static final String AFTER = "91iKJK";
    private static final RedditData REDDIT_DATA = TestUtil.createMockRedditData(AFTER);
    private static final RedditPost POST = TestUtil.createRedditPost("haha");

    @Mock private RedditDataSource remoteSource;
    private RedditRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        repository = new RedditRepository(remoteSource);
    }

    @Test
    public void getEntries_updateCache() {
        when(remoteSource.getEntries(AFTER, 10))
                .thenReturn(Observable.just(REDDIT_DATA));
        TestObserver<RedditData> testObserver = new TestObserver<>();
        repository.getEntries(AFTER, LIMIT)
                .subscribe(testObserver);
        verify(remoteSource).getEntries(AFTER, LIMIT);
        testObserver.assertValue(REDDIT_DATA);
        assertEquals(1, repository.mCache.size());
    }

    @Test
    public void getEntries_getCachedItems() {
        repository.mCache.put(AFTER, REDDIT_DATA);
        repository.getEntries(AFTER, LIMIT);
        verifyNoMoreInteractions(remoteSource);
    }

    @Test
    public void cachePost_savePost() {
        repository.cachePost(POST);
        assertEquals(repository.mCachedPost, POST);
    }

    @Test
    public void refresh_setDirtyCache() {
        repository.refresh();
        assertTrue(repository.mCacheIsDirty);
    }
}
