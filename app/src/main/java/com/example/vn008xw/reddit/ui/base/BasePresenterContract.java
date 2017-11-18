package com.example.vn008xw.reddit.ui.base;

public interface BasePresenterContract<T extends BaseView> {

    void attachView(T view);

    void removeView();
}
