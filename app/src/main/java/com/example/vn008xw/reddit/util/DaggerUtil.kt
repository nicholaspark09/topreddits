@file:JvmName("DaggerUtil")
package com.example.vn008xw.reddit.util

import android.util.Log
import com.example.vn008xw.reddit.BuildConfig

@Suppress("UNCHECKED_CAST")
fun <T> track(obj: Any): T {
  if (BuildConfig.DEBUG) Log.d("Dagger", "Created: ${obj.javaClass.simpleName}")
  return obj as T
}