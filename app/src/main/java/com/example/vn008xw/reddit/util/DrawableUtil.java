package com.example.vn008xw.reddit.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatDrawableManager;

public final class DrawableUtil {

    private DrawableUtil() {
        throw new AssertionError("No instances of a util class, please");
    }

    @SuppressLint("RestrictedApi")
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
        //noinspection RestrictedApi
        return AppCompatDrawableManager.get().getDrawable(context, resId);
    }
}
