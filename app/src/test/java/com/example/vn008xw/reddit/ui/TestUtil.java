package com.example.vn008xw.reddit.ui;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.vo.RedditData;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.data.vo.RedditPost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class TestUtil {

    private static final String[] CHILD_IDS =
            new String[]{"a", "b", "c", "d"};
    private static final String IMAGE_URL = "http://www.sdfdf.jpg";
    private static final String YOUTUBE = "https://www.youtu.be/dfjdkjf";

    private TestUtil() {
        throw new AssertionError("No instances");
    }

    public static RedditData createMockRedditData(@NonNull String afterKey) {
        final RedditData data = new RedditData();
        data.setAfter(afterKey);
        data.setChildren(createMockRedditChildren());
        return data;
    }

    public static List<RedditDataChild> createMockRedditChildren() {
        final List<RedditDataChild> children = new ArrayList<>();
        for (String id : CHILD_IDS) {
            children.add(createRedditDataChild(id));
        }
        return children;
    }

    public static RedditDataChild createRedditDataChild(@NonNull String id) {
        final RedditDataChild child = new RedditDataChild();
        child.setRedditPost(createRedditPost(id));
        return child;
    }

    public static RedditPost createRedditPost(@NonNull String id) {
        final RedditPost post = new RedditPost();
        post.setTitle("Title: " + id);
        post.setAuthor("Author of: " + id);
        post.setLikes(10);
        post.setDowns(2);
        post.setUrl(IMAGE_URL + id);
        post.setThumbnail(IMAGE_URL + id);
        post.setCreatedUtc(new Date().getTime());
        return post;
    }

    public static RedditPost createVideoPost(@NonNull String id) {
        final RedditPost post = createRedditPost(id);
        post.setUrl(YOUTUBE);
        return post;
    }
}
