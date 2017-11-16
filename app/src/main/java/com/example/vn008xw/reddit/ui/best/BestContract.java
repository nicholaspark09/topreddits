package com.example.vn008xw.reddit.ui.best;

import android.support.annotation.NonNull;

import com.example.vn008xw.reddit.data.vo.Entry;
import com.example.vn008xw.reddit.data.vo.Image;
import com.example.vn008xw.reddit.ui.base.BasePresenterContract;
import com.example.vn008xw.reddit.ui.base.BaseView;

import java.util.List;

public interface BestContract {

    interface View extends BaseView {
        void showEntries(@NonNull List<Entry> entries);
        void showImage(@NonNull Image image);
    }

    interface Presenter extends BasePresenterContract<View> {
        void getTopFifty();
        void refresh();
        void openImage(@NonNull Image image);
    }
}
