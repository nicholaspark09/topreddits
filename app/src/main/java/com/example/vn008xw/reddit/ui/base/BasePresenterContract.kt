package com.example.vn008xw.reddit.ui.base

interface BasePresenterContract<T : BaseView> {

  fun attachView(view: T)

  fun removeView()
}