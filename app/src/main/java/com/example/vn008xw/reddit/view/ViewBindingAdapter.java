package com.example.vn008xw.reddit.view;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ViewBindingAdapter {

    @BindingAdapter("imageResource")
    public static void setImageResource(@NonNull ImageView imageView, @DrawableRes int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(@NonNull ImageView imageView, @Nullable String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @BindingAdapter({"hidden"})
    public static void bindVisibility(View view, boolean hidden) {
        view.setVisibility(hidden ? View.GONE : View.VISIBLE);
    }
}