package com.jingjing.mytest.business;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.jingjing.mytest.R;
import com.jingjing.mytest.adapter.MydataAdapter;
import com.jingjing.mytest.utils.ToastUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private MydataAdapter mPicAdapter;
    private Context mContext;
    private ArrayList<Integer> mImgList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(view);
        mContext = this;
        mRecycleView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        mImgList = new ArrayList<>();
        mImgList.add(R.drawable.a);
        mImgList.add(R.drawable.b);
        mImgList.add(R.drawable.c);
        mImgList.add(R.drawable.d);
        mImgList.add(R.drawable.e);
        mImgList.add(R.drawable.f);
        mImgList.add(R.drawable.g);

        mPicAdapter = new MydataAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);


        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mPicAdapter);
        mPicAdapter.setOnItemClickListener(new MydataAdapter.OnItemClickListener() {
            @Override
            public void onImgItemClick(int position, int restId) {
                ToastUtils.showMessage(mContext, "" + position);
            }
        });
        mPicAdapter.appendData(mImgList);
        mRecycleView.scrollToPosition(0);
    }
}
