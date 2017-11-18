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
    private static final String URL = "http://www.sdfdf.jpg";

    private TestUtil() {
        throw new AssertionError("No instances");
    }

    public static RedditData getMockRedditData(@NonNull String afterKey) {
        final RedditData data = new RedditData();
        data.setAfter(afterKey);
        data.setChildren(getMockRedditChildren());
        return data;
    }

    public static List<RedditDataChild> getMockRedditChildren() {
        final List<RedditDataChild> children = new ArrayList<>();
        for (String id : CHILD_IDS) {
            children.add(getRedditDataChild(id));
        }
        return children;
    }

    public static RedditDataChild getRedditDataChild(@NonNull String id) {
        final RedditDataChild child = new RedditDataChild();
        child.setRedditPost(getRedditPost(id));
        return child;
    }

    public static RedditPost getRedditPost(@NonNull String id) {
        final RedditPost post = new RedditPost();
        post.setTitle("Title: " + id);
        post.setAuthor("Author of: " + id);
        post.setLikes(10);
        post.setDowns(2);
        post.setUrl(URL + id);
        post.setThumbnail(URL + id);
        post.setCreatedUtc(new Date().getTime());
        return post;
    }
}
