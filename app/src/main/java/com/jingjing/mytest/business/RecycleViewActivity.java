package com.jingjing.mytest.business;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jingjing.mytest.R;
import com.jingjing.mytest.adapter.MydataAdapter;
import com.jingjing.mytest.utils.ToastUtils;
import com.jingjing.mytest.view.CustomRecycleView;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    private CustomRecycleView mRecycleView;
    private MydataAdapter mPicAdapter;
    private Context mContext;
    private ArrayList<Integer> mImgList;
    private ImageView mIvPicDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_recycleview, null);
        setContentView(view);
        mContext = this;
        mRecycleView = (CustomRecycleView) view.findViewById(R.id.id_recyclerview);
        mIvPicDetail = (ImageView) view.findViewById(R.id.iv_pic_detail);

        mImgList = new ArrayList<>();
        mImgList.add(R.drawable.a);
        mImgList.add(R.drawable.b);
        mImgList.add(R.drawable.c);
        mImgList.add(R.drawable.d);
        mImgList.add(R.drawable.e);
        mImgList.add(R.drawable.f);
        mImgList.add(R.drawable.g);

        mRecycleView.setOnItemScrollChangeListener(new CustomRecycleView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                mIvPicDetail.setImageResource(mImgList.get(position));
            }
        });

        mPicAdapter = new MydataAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);


        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mPicAdapter);
        mPicAdapter.setOnItemClickListener(new MydataAdapter.OnItemClickListener() {
            @Override
            public void onImgItemClick(int position, int restId) {
                ToastUtils.showMessage(mContext, "" + position);
                mIvPicDetail.setImageResource(restId);
            }
        });
        mPicAdapter.appendData(mImgList);
        mRecycleView.scrollToPosition(0);
    }
}
