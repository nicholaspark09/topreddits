package com.example.vn008xw.reddit.ui.postimage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.vn008xw.reddit.R;
import com.example.vn008xw.reddit.databinding.ActivityPostImageBinding;
import com.example.vn008xw.reddit.ui.base.BaseActivity;
import com.example.vn008xw.reddit.util.DrawableUtil;
import com.example.vn008xw.reddit.util.PermissionsActivityHelper;
import com.example.vn008xw.reddit.util.PermissionsHelperContract;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public class PostImageActivity
        extends BaseActivity<PostImageContract.Presenter>
        implements PostImageContract.View,
        PermissionsHelperContract.Callback {

    private static final String ARG_IMAGE_URL = "arg:image_url";
    private static final String ARG_THUMB_URL = "arg:thumb_url";
    private static final String ARG_POST_ID = "arg:post_id";
    private static final String DISK_WRITE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String URI_SCHEME = "package";

    // Forcing the image into a smaller frame decreases the memory needed to retain it
    private static final int HEIGHT = 400;
    private static final int WIDTH = 350;

    @Nullable
    private ActivityPostImageBinding mBinding;
    @Nullable
    private Bitmap mBitmap;
    @NonNull
    private String mImageUrl = "";
    @NonNull
    private String mPostId = "";
    @NonNull
    private String mThumbUrl = "";
    @NonNull
    private final PermissionsActivityHelper mPermissionsHelper = PermissionsActivityHelper.newInstance(this);
    private boolean mTransitionStarted = false;

    public static Intent createIntent(@NonNull Context context,
                                      @NonNull String url,
                                      @NonNull String thumburl,
                                      @NonNull String id) {
        return new Intent(context, PostImageActivity.class)
                .putExtra(ARG_IMAGE_URL, url)
                .putExtra(ARG_THUMB_URL, thumburl)
                .putExtra(ARG_POST_ID, id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_image);
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        // Need to postpone transitions until the image has loaded
        // This only applies to versions lollipop and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportPostponeEnterTransition();
        }
        mImageUrl = getIntent().getStringExtra(ARG_IMAGE_URL);
        mPostId = getIntent().getStringExtra(ARG_POST_ID);
        mThumbUrl = getIntent().getStringExtra(ARG_THUMB_URL);
        mBinding.setLoading(true);
        mBinding.setIsSaved(false);
        loadThumbnail();
        getPresenter().start(mPostId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startLargeImage();
        }
    }

    private void loadThumbnail() {
        // We should already have a cached version
        Glide.with(this)
                .load(mThumbUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(DrawableUtil.getDrawable(this, R.drawable.ic_no_image))
                .into(new ViewTarget<ImageView, GlideDrawable>(mBinding.imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (resource instanceof GlideBitmapDrawable) {
                            mBinding.setLoading(false);
                            final Bitmap bitmap = ((GlideBitmapDrawable) resource).getBitmap();
                            getView().setImageBitmap(bitmap);
                            checkAndStartTransition();
                            mBinding.saveIndicator.setOnClickListener(v -> {
                                Timber.d("Clicked on image");
                                if (mBinding.getIsSaved()) {
                                    getPresenter().deleteImage();
                                } else {
                                    mBitmap = bitmap;
                                    mPermissionsHelper.makeRequest(DISK_WRITE_PERMISSION);
                                }
                            });
                        }
                    }
                });
    }

    private void loadLargeImage() {
        Glide.with(this)
                .load(mImageUrl)
                .fitCenter()
                .override(WIDTH, HEIGHT)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(DrawableUtil.getDrawable(this, R.drawable.ic_no_image))
                .into(new ViewTarget<ImageView, GlideDrawable>(mBinding.imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (resource instanceof GlideBitmapDrawable) {
                            mBinding.setLoading(false);
                            final Bitmap bitmap = ((GlideBitmapDrawable) resource).getBitmap();
                            getView().setImageBitmap(bitmap);
                            mBinding.saveIndicator.setOnClickListener(v -> {
                                Timber.d("Clicked on image");
                                if (mBinding.getIsSaved()) {
                                    getPresenter().deleteImage();
                                } else {
                                    mBitmap = bitmap;
                                    mPermissionsHelper.makeRequest(DISK_WRITE_PERMISSION);
                                }
                            });
                        }
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startLargeImage() {
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                loadLargeImage();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    @Override
    public boolean isActivityTransitionRunning() {
        return super.isActivityTransitionRunning();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionsHelper.onPermissionRequestResult(requestCode, permissions, grantResults);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void checkAndStartTransition() {
        if (!mTransitionStarted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportStartPostponedEnterTransition();
            mTransitionStarted = true;
        }
    }

    @Override
    public void showError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean isLoading) {
        mBinding.setLoading(isLoading);
    }

    @Override
    public void showSavedStatus(boolean isSaved) {
        mBinding.setIsSaved(isSaved);
        mBinding.executePendingBindings();
        if (isSaved && mBitmap != null) {
            mBitmap = null;
        }
    }

    @Override
    public void onPermissionGranted(@NonNull String permission) {
        if (permission == DISK_WRITE_PERMISSION && mBitmap != null) {
            getPresenter().saveImage(mBitmap);
        }
    }

    @Override
    public void onPermissionDeclined(@NonNull String permission) {
    } // NOP

    @Override
    public void onPermissionNeedsRationale(@NonNull String permission) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.post_activity_rationale_title)
                .setMessage(R.string.post_activity_rationale_message)
                .setPositiveButton(R.string.post_activity_rationale_update, (d, i) ->
                        mPermissionsHelper.makeRequest(DISK_WRITE_PERMISSION)
                )
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @Override
    public void onPermissionDeclinedForever(@NonNull String permission) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.post_activity_denied_title)
                .setMessage(R.string.post_activity_denied_message)
                .setPositiveButton(R.string.post_activity_denied_settings, (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts(URI_SCHEME, getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}
