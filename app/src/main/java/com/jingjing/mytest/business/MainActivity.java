package com.jingjing.mytest.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingjing.mytest.R;

/**
 * Author: KindyFung.
 * CreateTime:  2016/11/15 14:42
 * Email：fangjing@medlinker.com.
 * Description:
 */

public class MainActivity extends Activity {

    private int okId = 2 << 24 + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_1).setOnClickListener(onClickListener);
        findViewById(R.id.tv_2).setOnClickListener(onClickListener);
        findViewById(R.id.tv_3).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_1:
                    startActivity(new Intent(getBaseContext(), RecycleViewActivity.class));
                    break;
                case R.id.tv_2:
                    startActivity(new Intent(getBaseContext(), RichTextActivity.class));
                    break;
                case R.id.tv_3:
                    break;
            }
        }
    };
}
