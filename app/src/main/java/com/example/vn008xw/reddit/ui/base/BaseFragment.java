package com.example.vn008xw.reddit.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends BasePresenterContract>
        extends DaggerFragment
        implements BaseView {

    @Inject
    T mPresenter;

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onDetach() {
        mPresenter.removeView();
        super.onDetach();
    }

    @Override
    public void showError(@NotNull String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}