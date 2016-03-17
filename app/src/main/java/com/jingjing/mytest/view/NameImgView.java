package com.jingjing.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingjing.mytest.R;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/12 14:23
 * Email：fangjing@medlinker.com.
 * Description:带有名字的ImaegView
 */
public class NameImgView extends LinearLayout {

    private ImageView mIvPic;
    private TextView mTvPicDes;

    public NameImgView(Context context) {
        super(context);
        initView();
    }

    public NameImgView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_image_hold, this);
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        mTvPicDes = (TextView) findViewById(R.id.tv_pic_name);



    }

}
