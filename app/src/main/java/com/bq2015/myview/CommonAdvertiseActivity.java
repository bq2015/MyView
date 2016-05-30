package com.bq2015.myview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CommonAdvertiseActivity extends AppCompatActivity {

    private int[] images = {R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3,
            R.mipmap.icon4, R.mipmap.icon5, R.mipmap.icon6, R.mipmap.icon7,
    };

    private String[] titles = {"风吹轻轻", "春风得意", "那时风雨"
            , "青春不散", "还是一个人", "中秋佳节", "愉人自乐"};
    private LinearLayout mContainer;
    private TextView mTitle;
    private ViewPager mViewPager;
    private List<ImageView> mIamgeViews;
    private int mLastSelectPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_advertise);
        initView();
        initData();
    }

    private void initView() {
        //查找控件
        mContainer = (LinearLayout) findViewById(R.id.dost_container);
        mTitle = (TextView) findViewById(R.id.text_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void initData() {
        mTitle.setText(titles[0]);
        mViewPager.setAdapter(new ImagesAdapter());
        mIamgeViews = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            mIamgeViews.add(new ImageView(this));

            //动态添加点
            View dot = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            //最后一个点不需要margin
            if (i != images.length - 1) {
                layoutParams.rightMargin = 5;
            }

            if (i == 0) {
                dot.setBackgroundResource(R.drawable.dost_shape_selected);
            } else {
                dot.setBackgroundResource(R.drawable.dost_shape_normal);
            }
            dot.setLayoutParams(layoutParams);

            mContainer.addView(dot);
        }



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //循环切换,限止在0-图片个数之间
                position = position % mIamgeViews.size();
                mTitle.setText(titles[position]);

                View preDot = mContainer.getChildAt(mLastSelectPage);
                preDot.setBackgroundResource(R.drawable.dost_shape_normal);

                View currentDot =  mContainer.getChildAt(position);
                currentDot.setBackgroundResource(R.drawable.dost_shape_selected);

                mLastSelectPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //初始化ViewPager的初始位置
        int initPosition = Integer.MAX_VALUE / 2;
        initPosition = initPosition - initPosition % mIamgeViews.size();

        mViewPager.setCurrentItem(initPosition);
    }

    private class ImagesAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //循环切换,限止在0-图片个数之间
            position = position % mIamgeViews.size();
            //把页面的View添加到ViewPager里面
            ImageView page = mIamgeViews.get(position);
            page.setImageResource(images[position]);
            container.addView(page);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //循环切换,限止在0-图片个数之间
            position = position % mIamgeViews.size();
            //把页面的View添加到ViewPager里面
            ImageView page = mIamgeViews.get(position);
            container.removeView(page);
        }
    }
}
