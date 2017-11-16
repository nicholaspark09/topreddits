package com.example.vn008xw.reddit.ui.base

import android.support.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseView> : BasePresenterContract<T> {

  @VisibleForTesting
  val mDisposable = CompositeDisposable()

  @VisibleForTesting
  var mView: T? = null

  override fun attachView(view: T) {
    mView = view
  }

  override fun removeView() {
    mDisposable.clear()
    mView = null
  }
}