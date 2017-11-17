package com.example.vn008xw.reddit.util;


import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import com.example.vn008xw.reddit.data.vo.RedditPost;

import java.util.Date;

public final class TimeUtil {

    private TimeUtil() {
        throw new AssertionError("No instances");
    }

    public static CharSequence getTimeAgo(@NonNull RedditPost post) {
        final Date now = new Date();
        final Date pastDate = new Date(post.getCreatedUtc() * 1000);
        return DateUtils.getRelativeTimeSpanString(pastDate.getTime(), now.getTime(), DateUtils.MINUTE_IN_MILLIS);
    }
}
