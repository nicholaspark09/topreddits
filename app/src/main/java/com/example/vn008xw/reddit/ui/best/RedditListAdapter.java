package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.databinding.ListItemRedditPostBinding;
import com.example.vn008xw.reddit.view.DataBoundAdapter;

public class RedditListAdapter extends DataBoundAdapter<RedditDataChild, ListItemRedditPostBinding> {

    @NonNull
    private final ImageClickCallback mImageClickCallback;

    public RedditListAdapter(@NonNull ImageClickCallback clickCallback) {
        mImageClickCallback = clickCallback;
    }

    @Override
    protected ListItemRedditPostBinding createBinding(ViewGroup parent) {
        final ListItemRedditPostBinding binding =
                ListItemRedditPostBinding.inflate(LayoutInflater.from(parent.getContext()));
        binding.getRoot().setOnClickListener(v -> {
            final RedditPost post = binding.getPost();
            if (post != null && post.getThumbnail() != null) {
                mImageClickCallback.onClick(post.getUrl());
            }
        });
        return binding;
    }

    @Override
    protected void bind(ListItemRedditPostBinding binding, RedditDataChild item) {
        binding.setPost(item.getRedditPost());
    }

    @Override
    protected boolean areItemsTheSame(RedditDataChild oldItem, RedditDataChild newItem) {
        return oldItem.getRedditPost().getId().equals(newItem.getRedditPost().getId()) && oldItem.equals(newItem);
    }

    @Override
    protected boolean areContentsTheSame(RedditDataChild oldItem, RedditDataChild newItem) {
        return oldItem.getRedditPost().getTitle().equals(newItem.getRedditPost().getTitle()) && oldItem.equals(newItem);
    }

    public interface ImageClickCallback {
        void onClick(@NonNull String url);
    }
}
