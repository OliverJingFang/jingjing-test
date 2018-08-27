package com.jingjing.mytest.business;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jingjing.mytest.view.PathMeaView1;
import com.jingjing.mytest.view.WriteWordView;

/**
 * Author: FangJing.
 * CreateTime:  2018/8/23 15:31
 * Email：smilefangjing@163.com.
 * Description:
 */
public class PathDemoActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TwoCurveView twoCurveView = new TwoCurveView(PathDemoActivity.this);
//        WriteWordView writeWordView = new WriteWordView(PathDemoActivity.this);
        PathMeaView1 writeWordView = new PathMeaView1(PathDemoActivity.this);
        setContentView(writeWordView);
    }
}
