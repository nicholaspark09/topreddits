package com.example.vn008xw.reddit.ui.best;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vn008xw.reddit.R;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.databinding.FragmentBestBinding;
import com.example.vn008xw.reddit.ui.base.BaseFragment;
import com.example.vn008xw.reddit.ui.postimage.PostImageActivity;
import com.example.vn008xw.reddit.util.ItemDecorationUtil;

import java.util.List;

public class BestRedditsFragment extends BaseFragment<BestRedditsContract.Presenter>
        implements BestRedditsContract.View {

    @Nullable
    private FragmentBestBinding mBinding;
    @Nullable
    private RedditListAdapter mAdapter;

    public static BestRedditsFragment newInstance() {
        return new BestRedditsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentBestBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new RedditListAdapter(new RedditListAdapter.ImageClickCallback() {
            @Override
            public void onClick(@NonNull String url, @NonNull ImageView imageView) {
                showImage(url, imageView);
            }

            @Override
            public void onNoImage() {
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.list_item_no_image_title)
                        .setMessage(R.string.list_item_no_image_message)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            }
        });
        mBinding.recyclerView.setAdapter(mAdapter);
        ItemDecorationUtil.setStandardDecoration(mBinding.recyclerView);
        mBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isLastItemVisible(recyclerView) && (dy > 0)) {
                    getPresenter().getNextGroupOfPosts();
                }
            }
        });
        getPresenter().getNextGroupOfPosts();
    }

    @Override
    public void showLoading(boolean isLoading) {
        mBinding.setIsLoading(isLoading);
    }

    @Override
    public void showEntries(@NonNull List<RedditDataChild> entries) {
        if (mAdapter != null) {
            mAdapter.replace(entries);
        }
    }

    @Override
    public void showImage(@NonNull String thumbnail, @NonNull ImageView imageView) {
        final Intent intent = PostImageActivity.createIntent(getActivity(), thumbnail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            imageView,
                            ViewCompat.getTransitionName(imageView)
                    ).toBundle());
        } else {
            startActivity(intent);
        }
    }

    private boolean isLastItemVisible(@NonNull RecyclerView recyclerView) {
        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return manager.findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1;
    }
}