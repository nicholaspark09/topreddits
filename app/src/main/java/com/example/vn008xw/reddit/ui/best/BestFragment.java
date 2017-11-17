package com.example.vn008xw.reddit.ui.best;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.databinding.FragmentBestBinding;
import com.example.vn008xw.reddit.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by vn008xw on 11/17/17.
 */

public class BestFragment extends BaseFragment<BestContract.Presenter>
        implements BestContract.View {

    @Nullable
    private FragmentBestBinding mBinding;
    @Nullable
    private RedditListAdapter mAdapter;

    public static BestFragment newInstance() {
        return new BestFragment();
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
        mAdapter = new RedditListAdapter(url -> {
            getPresenter().openImage(url);
        });
        mBinding.recyclerView.setAdapter(mAdapter);
        getPresenter().getPosts("");
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
    public void showImage(@NonNull String thumbnail) {

    }
}
