package com.jingjing.mytest.view;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/16 15:14
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class ViewRecycler<T extends View> {
    private List<T> mCacheList = new ArrayList<>();

    public void cacheView(T view) {
        mCacheList.add(view);
    }

    public void cacheViews(List<T> views) {
        mCacheList.addAll(views);
    }

    public void cacheViews(ViewGroup viewGroup) {
        if (null != viewGroup) {
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                cacheView((T) viewGroup.getChildAt(i));
            }
        }
    }

    public T getOneView() {

        T item = null;
        if (mCacheList.size() > 0) {
            item = mCacheList.remove(0);
        }
        return item;
    }
}

