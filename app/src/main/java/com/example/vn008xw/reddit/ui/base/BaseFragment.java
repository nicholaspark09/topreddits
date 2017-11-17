package com.example.vn008xw.reddit.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.attachView(this);
    }

    @Override
    public void onDetach() {
        mPresenter.removeView();
        super.onDetach();
    }

    @Override
    public void showError(@NonNull String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}