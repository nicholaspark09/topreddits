package com.example.vn008xw.reddit.ui.postimage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.vn008xw.reddit.R;
import com.example.vn008xw.reddit.databinding.ActivityPostImageBinding;
import com.example.vn008xw.reddit.util.DrawableUtil;

public class PostImageActivity extends AppCompatActivity {

    private static final String ARG_IMAGE_URL = "arg:image_url";

    @Nullable
    private ActivityPostImageBinding mBinding;
    private boolean mTransitionStarted = false;

    public static Intent createIntent(@NonNull Context context, @NonNull String url) {
        return new Intent(context, PostImageActivity.class).putExtra(ARG_IMAGE_URL, url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Need to postpone transitions until the image has loaded
        // This only applies to versions lollipop and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportPostponeEnterTransition();
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_image);
        final String url = getIntent().getStringExtra(ARG_IMAGE_URL);
        Log.d("URL: ",  url);
        mBinding.setLoading(true);
        Glide.with(this)
                .load(url)
                .fitCenter()
                .error(DrawableUtil.getDrawable(this, R.drawable.ic_no_image))
                .into(new ViewTarget<SubsamplingScaleImageView, GlideDrawable>(mBinding.imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (resource instanceof GlideBitmapDrawable) {
                            mBinding.setLoading(false);
                            getView().setImage(ImageSource.bitmap(((GlideBitmapDrawable) resource).getBitmap()));
                            checkAndStartTransition();
                        }
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void checkAndStartTransition() {
        if (!mTransitionStarted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportStartPostponedEnterTransition();
            mTransitionStarted = true;
        }
    }
}
