package com.bq2015.myview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bq2015.myview.adapter.RecyclerViewAdapters;
import com.bq2015.myview.adapter.core.OnItemChildCheckedChangeListener;
import com.bq2015.myview.adapter.core.OnItemChildClickListener;
import com.bq2015.myview.bean.ActivityInfo;
import com.bq2015.myview.refreshlayout.BQMoocStyleRefreshViewHolder;
import com.bq2015.myview.refreshlayout.BQRefreshLayout;
import com.bq2015.myview.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 主页
 * Created by Kylin on 2016/5/27.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.main_rv_data)
    RecyclerView mRecyclerView;

    private final ActivityInfo[] activitys = getActivitys();
    @InjectView(R.id.main_rl_refreshview)
    BQRefreshLayout mBQRefreshLayout;
    private RecyclerViewAdapters mAdapter;
    private ActivityInfo[] mActivityInfos;
    private List<ActivityInfo> mActivityInfos1;

    private ActivityInfo[] getActivitys() {
        mActivityInfos = new ActivityInfo[]{
                new ActivityInfo("广告轮播图", "自定义控件", AdvertiseViewActivity.class),
        new ActivityInfo("自定滑动开关", "自定义控件", SwitchToggleViewActivity.class),
                new ActivityInfo("自定滑动开关", "自定义控件", CommonAdvertiseActivity.class)
        };
        return mActivityInfos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }


    /**
     * 初始化
     */
    private void init() {

        mAdapter = new RecyclerViewAdapters(mRecyclerView);
        mActivityInfos1 = Arrays.asList(mActivityInfos);
        mAdapter.setDatas(mActivityInfos1);

        /**
         * 条目点击事件
         */
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                Intent intent = new Intent(MainActivity.this,
                        mActivityInfos1.get(position).activityClass
                );

                startActivity(intent);
            }
        });

        mode1();

        mBQRefreshLayout.setDelegate(new BQRefreshLayout.RefreshLayoutDelegate() {
            @Override
            public void onRefreshLayoutBeginRefreshing(BQRefreshLayout refreshLayout) {
                //模拟延迟效果
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.setDatas(mActivityInfos1);
                        mBQRefreshLayout.endRefreshing();
                    }
                }, 2000);
            }

            @Override
            public boolean onRefreshLayoutBeginLoadingMore(BQRefreshLayout refreshLayout) {
                return false;
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }



    private void mode1() {
        BQMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BQMoocStyleRefreshViewHolder(this, true);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
        mBQRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
    }


}
