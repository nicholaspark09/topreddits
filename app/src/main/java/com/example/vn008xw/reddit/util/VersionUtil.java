package com.example.vn008xw.reddit.util;

import android.os.Build;

public final class VersionUtil {

    private VersionUtil() {
        throw new AssertionError("No instances");
    }

    public static boolean isLollipopOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
