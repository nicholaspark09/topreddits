package com.example.vn008xw.reddit.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.vn008xw.reddit.R
import com.example.vn008xw.reddit.ui.best.BestFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject lateinit var injector: DispatchingAndroidInjector<Fragment>

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      val fragment = BestFragment.newInstance()
      supportFragmentManager.beginTransaction()
          .replace(R.id.content_frame, fragment)
          .commit()
    }
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector
}