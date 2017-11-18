package com.example.vn008xw.reddit.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

public final class PackageUtil {

    private PackageUtil() {
        throw new AssertionError("No instances");
    }

    public static boolean isPackagePresent(@NonNull Context context,
                                           @NonNull Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_ALL).size() > 0;
    }
}
