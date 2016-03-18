package com.jingjing.mytest.business;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingjing.mytest.R;
import com.jingjing.mytest.entity.TestData;
import com.jingjing.mytest.utils.ToastUtils;
import com.jingjing.mytest.view.ColumnLayout;

import java.util.ArrayList;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/18 14:15
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class ColumnActivity extends Activity {

    private ColumnLayout mClLay;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layoutView = LayoutInflater.from(this).inflate(R.layout.activity_column, null);
        setContentView(R.layout.activity_column);
        mContext = this;
        mClLay = (ColumnLayout) layoutView.findViewById(R.id.cl_my_test);
        setLayoutDdata();
    }

    private void setLayoutDdata() {
        ArrayList<TestData> dataList = new ArrayList<>();
        TestData data = new TestData();
        data.setId(R.drawable.a);
        data.setDes("第一张图");
        dataList.add(data);

        TestData data1 = new TestData();
        data1.setId(R.drawable.b);
        data1.setDes("第二张图");
        dataList.add(data1);

        TestData data2 = new TestData();
        data2.setId(R.drawable.c);
        data2.setDes("第三张图");
        dataList.add(data2);

        TestData data3 = new TestData();
        data3.setId(R.drawable.d);
        data3.setDes("第四张图");
        dataList.add(data3);

        TestData data4 = new TestData();
        data4.setId(R.drawable.e);
        data4.setDes("第五张图");
        dataList.add(data4);

        mClLay.setData(dataList);
        mClLay.setViewBinder(new ColumnLayout.ViewBinder() {
            @Override
            public View setViewValue(View childView, int position) {
                TestData tst = (TestData) mClLay.getItem(position);
                TextView tvSrc = (TextView) childView.findViewById(R.id.tv_pic_name);
                ImageView ivSrc = (ImageView) childView.findViewById(R.id.iv_pic);
                tvSrc.setText(tst.getDes());
                ivSrc.setImageResource(tst.getId());
                childView.setTag(tst);
                return childView;
            }
        });

        mClLay.setData(dataList);
        mClLay.setOnItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestData entity = (TestData) v.getTag();
                ToastUtils.showMessage(mContext, "" + entity.getDes());
            }
        });
    }
}
