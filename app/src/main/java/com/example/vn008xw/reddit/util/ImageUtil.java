package com.example.vn008xw.reddit.util;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.vo.RedditPost;

public final class ImageUtil {

    private static final String IMAGE_KEY = ".jpg";
    private static final String YOUTUBE_KEY = "you";

    private ImageUtil() {
        throw new AssertionError("No instances");
    }

    public static boolean hasImageUrl(@NonNull RedditPost post) {
        return post.getThumbnail() != null && post.getThumbnail().contains(IMAGE_KEY);
    }

    public static boolean hasLargeImage(@NonNull String url) {
        return url.contains(IMAGE_KEY);
    }

    public static boolean isYoutubeVideo(@NonNull String url) {
        return url.contains(YOUTUBE_KEY);
    }
}