package com.jingjing.mytest.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/12 12:06
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class CustomRecycleView extends RecyclerView {

    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private View mCurrentView;

    /**
     * 滚动时回调的接口
     */
    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);
        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,
                    getChildPosition(mCurrentView));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentView = getChildAt(0);
            // Log.e("TAG", getChildPosition(getChildAt(0)) + "");
            if (mItemScrollChangeListener != null) {
                mItemScrollChangeListener.onChange(mCurrentView,
                        getChildPosition(mCurrentView));
            }
        }

        return super.onTouchEvent(e);
    }
}
