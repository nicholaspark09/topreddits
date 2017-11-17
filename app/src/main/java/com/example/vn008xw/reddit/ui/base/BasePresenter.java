package com.example.vn008xw.reddit.ui.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends BaseView> implements BasePresenterContract<T> {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

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