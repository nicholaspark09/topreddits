package com.example.vn008xw.reddit.ui.redditpost;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.vn008xw.reddit.R;
import com.example.vn008xw.reddit.data.vo.RedditPost;
import com.example.vn008xw.reddit.databinding.ActivityRedditPostDetailBinding;
import com.example.vn008xw.reddit.ui.base.BaseActivity;
import com.example.vn008xw.reddit.util.DrawableUtil;
import com.example.vn008xw.reddit.util.PackageUtil;

import timber.log.Timber;

public class RedditPostDetailActivity
        extends BaseActivity<RedditPostDetailContract.Presenter>
        implements RedditPostDetailContract.View {

    private static final String KEY_POST_ID = "key:post_id";


    @Nullable
    private ActivityRedditPostDetailBinding mBinding;
    @NonNull
    private String mPostId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Bundle bundle = savedInstanceState != null
                ? savedInstanceState : getIntent().getExtras();
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_reddit_post_detail);
        setSupportActionBar(mBinding.postAppBar.toolbar);

        mPostId = bundle.getString(KEY_POST_ID);
        getPresenter().findPost(mPostId);
    }

    @Override
    public void showError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean isLoading) {
        mBinding.postAppBar.setIsLoading(isLoading);
    }

    @Override
    public void showPostDetails(@NonNull RedditPost post) {
        mBinding.setPost(post);
    }

    @Override
    public void showThumbnailImage(@NonNull RedditPost post) {
        mBinding.postAppBar.setHasImage(true);
        showLoading(false);
        Glide.with(this)
                .load(post.getThumbnail())
                .fitCenter()
                .error(DrawableUtil.getDrawable(this, R.drawable.ic_no_image))
                .into(new ViewTarget<ImageView, GlideDrawable>(mBinding.postAppBar.imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (resource instanceof GlideBitmapDrawable) {
                            showLoading(false);
                            final Bitmap bitmap = ((GlideBitmapDrawable) resource).getBitmap();
                            getView().setImageBitmap(bitmap);
                            getPresenter().openLargeImage(post.getId());
                        }
                    }
                });
    }

    @Override
    public void showLargeImage(@NonNull String imageUrl) {
        showLoading(true);
        Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(DrawableUtil.getDrawable(this, R.drawable.ic_no_image))
                .into(new ViewTarget<ImageView, GlideDrawable>(mBinding.postAppBar.imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        showLoading(false);
                        if (resource instanceof GlideBitmapDrawable) {
                            showLoading(false);
                            final Bitmap bitmap = ((GlideBitmapDrawable) resource).getBitmap();
                            getView().setImageBitmap(bitmap);
                        }
                    }
                });
    }

    @Override
    public void showVideoIsYoutube() {
        mBinding.postAppBar.setIsYoutube(true);
        mBinding.postAppBar.youtubeButton.setOnClickListener(v ->
                getPresenter().openYoutubeVideo(mPostId)
        );
    }

    @Override
    public void showYoutubeVideo(@NonNull String url) {
        final Uri uri = Uri.parse(url);
        final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // Can't be done in the presenter as the context is needed
        if (PackageUtil.isPackagePresent(this, intent)) {
            startActivity(intent);
        } else {
            showError(getString(R.string.reddit_post_youtube_error));
        }
    }

    @Override
    public void showNoImage() {
        mBinding.postAppBar.appbar.setVisibility(View.GONE);
    }

    @Override
    public void showNoPostError() {
        // If no post has been found, it doesn't exist in the cache
        // There is a logical error somewhere, but this error is too high to just show a message
        // Must force back
        Timber.e("The post wasn't found or in the repository");
        finish();
    }
}
