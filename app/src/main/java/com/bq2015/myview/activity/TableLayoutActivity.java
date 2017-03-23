package com.bq2015.myview.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.bq2015.myview.R;
import com.bq2015.myview.adapter.SimpleFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Kylin
 * Date：2017/3/23:10:09
 * Description：//todo
 */
public class TableLayoutActivity extends FragmentActivity {
    @BindView(R.id.sliding_tabs)
    android.support.design.widget.TabLayout mSlidingTabs;
    @BindView(R.id.vp_tab_layout)
    ViewPager mVpTabLayout;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_demo);
        ButterKnife.bind(this);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.vp_tab_layout);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setTabMode(TabLayout.GRAVITY_FILL);//选中居中
//        tabLayout.setTabMode(TabLayout.GRAVITY_CENTER);//选中居中
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//选中居中


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }

    }


}
