package com.example.vn008xw.reddit.ui.postimage;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.ui.base.BasePresenterContract;
import com.example.vn008xw.reddit.ui.base.BaseView;

public interface PostImageContract {

    interface View extends BaseView {
        void showSavedStatus(boolean isSaved);
    }

    interface Presenter extends BasePresenterContract<View> {
        void start(@NonNull String id);

        void observeSavedStatus();

        void saveImage(@NonNull Bitmap bitmap);

        void deleteImage();
    }
}