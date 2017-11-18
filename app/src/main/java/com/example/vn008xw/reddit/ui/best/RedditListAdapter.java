package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.databinding.RedditPostCardBinding;
import com.example.vn008xw.reddit.view.DataBoundAdapter;

public class RedditListAdapter extends DataBoundAdapter<RedditDataChild, RedditPostCardBinding> {

    private static final String KEY_IN_THUMB = "http";

    @NonNull
    private final ImageClickCallback mImageClickCallback;

    public RedditListAdapter(@NonNull ImageClickCallback clickCallback) {
        mImageClickCallback = clickCallback;
    }

    @Override
    protected RedditPostCardBinding createBinding(ViewGroup parent) {
        final RedditPostCardBinding binding =
                RedditPostCardBinding.inflate(LayoutInflater.from(parent.getContext()));
        binding.imageView.setOnClickListener(v -> {
            final RedditPost post = binding.getPost();
            mImageClickCallback.onImageClicked(post, binding.imageView);
        });
        binding.titleTextView.setOnClickListener(v -> {
            final RedditPost post = binding.getPost();
            mImageClickCallback.onTitleClicked(
                    post,
                    binding.imageView,
                    binding.titleTextView,
                    binding.authorTextView
            );
        });
        return binding;
    }

    @Override
    protected void bind(RedditPostCardBinding binding, RedditDataChild item) {
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
        // The imageview and textview are being sent for transition purposes
        void onTitleClicked(@NonNull RedditPost post,
                            @NonNull ImageView imageView,
                            @NonNull TextView titleView,
                            @NonNull TextView authorView);

        void onImageClicked(@NonNull RedditPost post, @NonNull ImageView imageView);
    }
}
