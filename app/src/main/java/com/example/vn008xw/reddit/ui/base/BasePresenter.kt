package com.example.vn008xw.reddit.ui.base

import android.support.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseView> : BasePresenterContract<T> {

  @VisibleForTesting
  val disposable = CompositeDisposable()

  @VisibleForTesting
  var view: T? = null

  override fun attachView(v: T) {
    view = v
  }

  override fun removeView() {
    disposable.clear()
    view = null
  }
}