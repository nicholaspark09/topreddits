package com.example.vn008xw.reddit.data.image;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import io.reactivex.Observable;


public interface ImageDataSource {

    Observable<String> saveImage(@NonNull String url, @NonNull Bitmap bitmap);
}
