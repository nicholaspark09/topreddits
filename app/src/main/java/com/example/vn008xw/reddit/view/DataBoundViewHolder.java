package com.example.vn008xw.reddit.view;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * A generic ViewHolder that wraps a generated ViewDataBinding class.
 *
 * From: https://github.com/google/android-ui-toolkit-demos/blob/master/DataBinding/
 * DataBoundRecyclerView/app/src/main/java/com/example/android/databoundrecyclerview/
 * DataBoundViewHolder.java
 * @param <T> The type of the ViewDataBinding class
 */
public class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public final T binding;

    DataBoundViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}