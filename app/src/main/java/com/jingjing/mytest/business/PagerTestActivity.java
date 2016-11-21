package com.jingjing.mytest.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.jingjing.mytest.R;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.ButterKnife;

/**
 * Author: KindyFung.
 * CreateTime:  2016/11/21 14:56
 * Email：fangjing@medlinker.com.
 * Description:
 */

public class PagerTestActivity extends AppCompatActivity{

    private TabPageIndicator tabPageIndicator;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view  = LayoutInflater.from(this).inflate(R.layout.activity_pager_test,null);
        setContentView(view);
        ButterKnife.bind(this,view);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabPageIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //实例化TabPageIndicator然后设置ViewPager与之关联
        tabPageIndicator.setViewPager(viewPager);

        //如果我们要对ViewPager设置监听，用indicator设置就行了
        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                Toast.makeText(getApplicationContext(), TITLE[arg0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[] { "头条", "房产", "另一面", "女人",
            "财经", "数码", "情感", "科技" };



    /**
     * ViewPager适配器
     * @author len
     *
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = new ItemFragment();
            Bundle args = new Bundle();
            args.putString("arg", TITLE[position]);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }




}
