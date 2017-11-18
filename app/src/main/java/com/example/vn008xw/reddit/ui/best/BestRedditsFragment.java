package com.example.vn008xw.reddit.ui.best;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vn008xw.reddit.R;
import com.example.vn008xw.reddit.data.vo.RedditDataChild;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.databinding.FragmentBestBinding;
import com.example.vn008xw.reddit.ui.base.BaseFragment;
import com.example.vn008xw.reddit.ui.postimage.PostImageActivity;
import com.example.vn008xw.reddit.ui.redditpost.RedditPostDetailActivity;
import com.example.vn008xw.reddit.util.VersionUtil;

import java.util.List;

public class BestRedditsFragment extends BaseFragment<BestRedditsContract.Presenter>
        implements BestRedditsContract.View {

    private static final String KEY_POST_ID = "key:post_id";

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
            public void onTitleClicked(@NonNull RedditPost post,
                                       @NonNull ImageView imageView,
                                       @NonNull TextView titleView,
                                       @NonNull TextView authorView) {
                getPresenter().openRedditDetail(post, titleView, authorView, imageView);
            }

            @Override
            public void onImageClicked(@NonNull RedditPost post, @NonNull ImageView imageView) {
                getPresenter().openImage(post, imageView);
            }

        });
        mBinding.recyclerView.setAdapter(mAdapter);
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
    public void showNoImage() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.list_item_no_image_title)
                .setMessage(R.string.list_item_no_image_message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void showImage(@NonNull RedditPost post, @NonNull ImageView imageView) {
        final Intent intent =
                PostImageActivity.createIntent(getActivity(), post.getUrl(), post.getThumbnail(), post.getId());
        if (VersionUtil.isLollipopOrHigher()) {
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

    @Override
    public void showRedditDetail(boolean hasImage,
                                 @NonNull String postId,
                                 @NonNull TextView titleView,
                                 @NonNull TextView authorView,
                                 @Nullable ImageView imageView) {

        final Intent intent = new Intent(getActivity(), RedditPostDetailActivity.class);
        intent.putExtra(KEY_POST_ID, postId);

        if (VersionUtil.isLollipopOrHigher()) {
            final ActivityOptionsCompat options;
            final Pair<View, String> titlePair = Pair.create(titleView, titleView.getTransitionName());
            final Pair<View, String> authorPair = Pair.create(authorView, authorView.getTransitionName());
            if (hasImage) {
                final Pair<View, String> imagePair = Pair.create(imageView, imageView.getTransitionName());
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        titlePair,
                        authorPair,
                        imagePair
                );
            } else {
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        titlePair,
                        authorPair
                );
            }
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private boolean isLastItemVisible(@NonNull RecyclerView recyclerView) {
        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return manager.findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1;
    }
}