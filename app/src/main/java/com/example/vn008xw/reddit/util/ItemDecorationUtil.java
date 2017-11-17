package com.example.vn008xw.reddit.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

public final class ItemDecorationUtil {

    private ItemDecorationUtil() {
        throw new AssertionError("No instances");
    }

    public static void setStandardDecoration(@NonNull RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            final DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    ((LinearLayoutManager) layoutManager).getOrientation());
            recyclerView.addItemDecoration(itemDecoration);
        }
    }

    public static int dpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
