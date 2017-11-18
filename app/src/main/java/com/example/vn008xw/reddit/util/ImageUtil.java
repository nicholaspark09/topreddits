package com.example.vn008xw.reddit.util;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.vo.RedditPost;

public final class ImageUtil {

    private static final String URL_BASE = "http";

    private ImageUtil() {
        throw new AssertionError("No instances");
    }

    public static boolean hasImageUrl(@NonNull RedditPost post) {
        return post.getThumbnail() != null && post.getThumbnail().contains(URL_BASE);
    }
}