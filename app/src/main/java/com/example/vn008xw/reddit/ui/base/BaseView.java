package com.example.vn008xw.reddit.ui.base;

import android.support.annotation.NonNull;

public interface BaseView {
    void showError(@NonNull String message);

    void showLoading(boolean isLoading);
}
