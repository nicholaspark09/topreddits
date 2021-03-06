package com.example.vn008xw.reddit.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<T extends BasePresenterContract>
        extends AppCompatActivity
        implements BaseView, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> mInjector;

    @Inject
    T mPresenter;

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        androidInject();
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
    }

    protected void androidInject() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.removeView();
        super.onDestroy();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return mInjector;
    }
}