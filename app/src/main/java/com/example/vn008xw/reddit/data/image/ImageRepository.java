package com.example.vn008xw.reddit.data.image;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import timber.log.Timber;

public class ImageRepository implements ImageDataSource {

    @NonNull
    private final String mAppName;
    @NonNull
    private final Subject<Boolean> mSavedSubject = BehaviorSubject.createDefault(false);
    private String mPostId = "";

    @Inject
    public ImageRepository(@NonNull @Named("AppName") String appName) {
        mAppName = appName;
    }

    @Override
    public void setPostId(@NonNull String id) {
        mPostId = id;
        mSavedSubject.onNext(getFileExists(mPostId));
    }

    @Override
    public Observable<Boolean> getSavedStatus() {
        return mSavedSubject;
    }

    @Override
    public Observable<String> saveImage(@NonNull Bitmap bitmap) {
        return Observable.create(subscriber -> {
            final String filePath = getFilePath();
            final File file = new File(filePath);
            final FileOutputStream outputStream = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                outputStream.flush();
                outputStream.close();
                subscriber.onNext(file.getAbsolutePath());
                mSavedSubject.onNext(true);
            } catch (Exception e) {
                subscriber.onError(e);
                Timber.e(e, "Exception: " + e.getMessage());
                mSavedSubject.onNext(false);
            }
        });
    }

    @NonNull
    @Override
    public String getPhotoUrl() {
        final File file = new File(getFilePath());
        if (file.exists()) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }

    @Override
    public void deleteImage() {
        final File file = new File(getFilePath());
        if (file.exists() && file.delete()) {
            Timber.d("Deleting image");
            mSavedSubject.onNext(false);
        }
    }

    @NonNull
    private File getDirectory() {
        final File directory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/" + mAppName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    private boolean getFileExists(@NonNull String id) {
        final File file = new File(getFilePath());
        return file.exists();
    }

    private String getFilePath() {
        final File directory = getDirectory();
        final StringBuilder builder = new StringBuilder(directory.getAbsolutePath())
                .append("/")
                .append(mPostId)
                .append(".jpg");
        return builder.toString();
    }
}
