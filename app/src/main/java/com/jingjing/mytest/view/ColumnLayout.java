package com.jingjing.mytest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.jingjing.mytest.R;
import com.jingjing.mytest.utils.DeviceUtil;
import com.jingjing.mytest.utils.DimenUtil;
import com.jingjing.mytest.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:yangjiantao@medlinker.net">Jiantao.Yang</a>
 * @version 3.0
 * @description 纵行布局组件
 * @time 2015/10/22 15:12
 */
public class ColumnLayout extends LinearLayout implements View.OnClickListener {

    private final int DIVIDER_TAG = 1444;

    protected LayoutInflater mInflater;

    protected Context mContext;

    private int mChildLayoutId = -1;

    private int mColumns = 1;

    private OnClickListener mClickListener;

    private float dividerH, cDividerW;

    private int divider = -1, cDivider = -1;

    private float childPadding = 0;
    private float childPaddingTop = 0;
    private float childPaddingBottom = 0;

    private ViewBinder mViewBinder;

    private ViewRecycler<LinearLayout> mParentRecycler;

    private ViewRecycler<View> mChildRecycler;

    private List mDatas;
    private boolean mAutoHeight;
    private boolean mShowFinalBottomLine;

    /**
     * @param context
     * @param attrs
     */
    public ColumnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColumnLayout);
        mChildLayoutId = a.getResourceId(R.styleable.ColumnLayout_childLayout, -1);
        divider = a.getResourceId(R.styleable.ColumnLayout_rowDivider, -1);
        cDivider = a.getResourceId(R.styleable.ColumnLayout_childDivider, -1);
        mColumns = a.getInt(R.styleable.ColumnLayout_column, 1);
        dividerH = a.getDimension(R.styleable.ColumnLayout_rowDividerHeight, -1);
        cDividerW = a.getDimension(R.styleable.ColumnLayout_childDividerWidth, -1);
        childPadding = a.getDimension(R.styleable.ColumnLayout_childPadding, 0);
        childPaddingTop = a.getDimension(R.styleable.ColumnLayout_childPaddingTop, 0);
        childPaddingBottom = a.getDimension(R.styleable.ColumnLayout_childPaddingBottom, 0);
        mAutoHeight = a.getBoolean(R.styleable.ColumnLayout_autoHeight, false);
        mShowFinalBottomLine = a.getBoolean(R.styleable.ColumnLayout_showFinalBottomLine, true);
        setOrientation(LinearLayout.VERTICAL);
        a.recycle();
    }

    /**
     * note：在setData方法前调用
     *
     * @param viewBinder
     */
    public void setViewBinder(ViewBinder viewBinder) {
        mViewBinder = viewBinder;
        mParentRecycler = new ViewRecycler<>();
        mChildRecycler = new ViewRecycler<>();
    }

    public void setData(List datas) {
        if (null == mDatas) {
            mDatas = new ArrayList();
        } else {
            mDatas.clear();
        }
        if (datas != null && !datas.isEmpty()) {
            mDatas.addAll(datas);
        }
        notifyDataChanged();
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void notifyDataChanged() {
        int childCount = getCount();
        if (childCount == 0 || mColumns == 0 || mViewBinder == null) {
            return;
        }
        if (childCount > 0) {
            cacheViews();
            removeAllViews();
            int rows = (childCount + mColumns - 1) / mColumns;
            int temp = 0;
            for (int i = 0; i < rows; i++) {
                LinearLayout layout = mParentRecycler.getOneView();
                if (layout == null) {
                    layout = new LinearLayout(mContext);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.WRAP_CONTENT));
                }else{
                    LogUtil.e("yjt","ColumnLayout useCacheViews layouts!!!");
                }
                for (int j = 0; j < mColumns; j++) {
                    int position = i * mColumns + j;
                    View child = mChildRecycler.getOneView();
                    if (child == null) {
                        child = mInflater.inflate(mChildLayoutId, null);
                    }else{
                        LogUtil.e("yjt","ColumnLayout useCacheViews childs!!!");
                    }

                    if (position < childCount) {
                        child = mViewBinder.setViewValue(child, position);
                    }
                    ViewGroup.LayoutParams originalParams = child.getLayoutParams();
                    int height = LayoutParams.WRAP_CONTENT;
                    if (originalParams != null) {
                        height = originalParams.height;
                    }
                    LayoutParams leafLayoutParams = new LayoutParams(0, height, 1);
                    child.setLayoutParams(leafLayoutParams);
                    if (mAutoHeight) {
                        child.setMinimumHeight((DeviceUtil.getScreenWidth(getContext()) - getPaddingLeft() - getPaddingRight()) / mColumns);
                    }
                    child.setPadding(DimenUtil.dip2px(getContext(),childPadding), DimenUtil.dip2px(getContext(),childPaddingTop), DimenUtil.dip2px(getContext(),childPadding), DimenUtil.dip2px(getContext(),childPaddingBottom));
                    layout.addView(child);
                    int index = temp ++;
                    if (index < childCount) {
                        child.setOnClickListener(this);
                        child.setVisibility(View.VISIBLE);
                    } else {
                        child.setVisibility(View.INVISIBLE);
                    }
                    // 添加 child divider
                    if ((index < childCount) && (j != mColumns - 1) && (cDivider != -1)) {
                        View childDivider = new View(mContext);

                        LayoutParams cdLayoutParams =
                                new LayoutParams(getChildDividerWidth(), LayoutParams.MATCH_PARENT);
                        childDivider.setLayoutParams(cdLayoutParams);
                        childDivider.setBackgroundResource(cDivider);
                        childDivider.setTag(DIVIDER_TAG);
                        layout.addView(childDivider);
                    }
                }
                addView(layout);
                // 添加divider
                int rowDividerIndex = mShowFinalBottomLine ? rows : rows - 1;
                if ((i < rowDividerIndex) && (divider != -1)) {
                    View rowDivider = new View(mContext);
                    rowDivider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, getDividerHeight()));
                    rowDivider.setBackgroundResource(divider);
                    rowDivider.setTag(DIVIDER_TAG);
                    addView(rowDivider);
                }
            }
        }
    }

    public void setOnItemClick(OnClickListener onItemClickListener) {
        mClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (null != mClickListener) {
            mClickListener.onClick(v);
        }
    }


    /**
     * Creates views in a ColumnLayout.
     */
    public interface ViewBinder {
        /**
         * @return a {@link android.view.View}
         */
//        View makeView(int childLayoutId, int position);
        View setViewValue(View childView, int position);
    }

    private int getChildDividerWidth() {
        int dividerW = (int) cDividerW;
        return (dividerW <= 0 ? DimenUtil.dip2px(getContext(),0.5f) : dividerW);
    }

    private int getDividerHeight() {
        int dHeight = (int) dividerH;
        return (dHeight <= 0 ? DimenUtil.dip2px(getContext(),0.5f) : dHeight);
    }

    private void cacheViews() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            try {
                LinearLayout rowLayout = (LinearLayout) getChildAt(i);
                mParentRecycler.cacheView(rowLayout);
                cacheChildViews(rowLayout);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    private void cacheChildViews(LinearLayout rowLayout) {
        int count = rowLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = rowLayout.getChildAt(i);
            Object tag = child.getTag();
            if (tag == null) {//divider view设置了tag
                mChildRecycler.cacheView(child);
            }
        }
        rowLayout.removeAllViews();
    }

}
