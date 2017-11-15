package com.example.vn008xw.reddit.ui.base

import io.reactivex.disposables.CompositeDisposable


class BasePresenter<T : BaseView> : BasePresenterContract<T> {

  var compositeDisposable = CompositeDisposable()

  var view: T? = null

  override fun attachView(view: T) {
    this.view = view
  }

  override fun removeView() {
    compositeDisposable.clear()
    view = null
  }
}