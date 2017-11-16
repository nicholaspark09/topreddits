package com.example.vn008xw.reddit.ui.best

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vn008xw.reddit.R
import com.example.vn008xw.reddit.data.vo.Entry
import com.example.vn008xw.reddit.data.vo.Image
import com.example.vn008xw.reddit.ui.base.BaseFragment


class BestFragment : BaseFragment<BestContract.Presenter>(), BestContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_best, container, false)
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
  }

  override fun onDetach() {
    super.onDetach()
  }

  override fun showLoading(isLoading: Boolean) {

  }

  override fun showEntries(entries: List<Entry>) {

  }

  override fun showImage(image: Image) {

  }

  companion object {
    @JvmStatic
    fun newInstance(): BestFragment {
      val fragment = BestFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}
