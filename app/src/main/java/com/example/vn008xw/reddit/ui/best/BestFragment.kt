package com.example.vn008xw.reddit.ui.best

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vn008xw.reddit.data.vo.RedditDataChild
import com.example.vn008xw.reddit.databinding.FragmentBestBinding
import com.example.vn008xw.reddit.ui.base.BaseFragment


class BestFragment : BaseFragment<BestContract.Presenter>(), BestContract.View {

  private lateinit var binding: FragmentBestBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?) =
      FragmentBestBinding.inflate(inflater, container, false).apply {
        binding = this
      }.root

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    presenter.getTopFifty()
  }

  override fun showLoading(isLoading: Boolean) {
      binding.isLoading = isLoading
  }

  override fun showEntries(entries: List<RedditDataChild>) {

  }

  override fun showImage(thumbail: String) {

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
