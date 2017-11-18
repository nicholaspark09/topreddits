package com.example.vn008xw.reddit.ui.base;

import android.support.annotation.VisibleForTesting;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends BaseView> implements BasePresenterContract<T> {

    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private T mView;

    public T getView() {
        return mView;
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mCompositeDisposable.clear();
        mView = null;
    }
}