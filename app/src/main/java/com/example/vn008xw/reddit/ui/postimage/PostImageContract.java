package com.example.vn008xw.reddit.ui.postimage;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.ui.base.BasePresenterContract;
import com.example.vn008xw.reddit.ui.base.BaseView;

public interface PostImageContract {

    interface View extends BaseView {
        void showSaveSuccess(@NonNull String imageUrl);
        void showGalleryImage(@NonNull String imageUrl);
    }

    interface Presenter extends BasePresenterContract<View> {
        void saveImage(@NonNull String url, @NonNull Bitmap bitmap);
        void unsaveImage(@NonNull String url);
    }
}
