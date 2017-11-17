package com.example.vn008xw.reddit.data.image;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

public class ImageRepository implements ImageDataSource {

    @NonNull
    private final String mAppName;

    @Inject
    public ImageRepository(@NonNull @Named("AppName") String appName) {
        mAppName = appName;
    }

    @Override
    public Observable<String> saveImage(@NonNull String url, @NonNull Bitmap bitmap) {
        return Observable.create(subscriber -> {
            final File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + mAppName);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            final File file = new File(directory.getAbsolutePath() + "/" +
                    url + ".jpg");
            final FileOutputStream outputStream = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                outputStream.flush();
                outputStream.close();
                subscriber.onNext(file.getAbsolutePath());
            } catch (Exception e) {
                subscriber.onError(new IOException());
            }
        });
    }
}
