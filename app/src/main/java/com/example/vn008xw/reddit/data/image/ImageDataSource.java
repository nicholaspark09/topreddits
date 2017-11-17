package com.example.vn008xw.reddit.data.image;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import io.reactivex.Observable;


public interface ImageDataSource {

    void setPostId(@NonNull String id);

    @NonNull
    Observable<Boolean> getSavedStatus();

    @NonNull
    Observable<String> saveImage(@NonNull Bitmap bitmap);

    @NonNull
    String getPhotoUrl();

    void deleteImage();
}
