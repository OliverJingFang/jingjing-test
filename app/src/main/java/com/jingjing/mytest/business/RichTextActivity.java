package com.jingjing.mytest.business;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jingjing.mytest.R;
import com.jingjing.mytest.view.RichEditText;

/**
 * Author: KindyFung.
 * CreateTime:  2016/8/2 11:41
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class RichTextActivity extends AppCompatActivity {

    private RichEditText mEtRich;
    private String path = "/storage/emulated/0/自然风光/CoolShow_E000010 - 副本.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_richtext);
        mEtRich = (RichEditText)findViewById(R.id.ret_content);
        Button button = (Button)findViewById(R.id.add_pic);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtRich.insertBitmap(path);
            }
        });


    }

}
