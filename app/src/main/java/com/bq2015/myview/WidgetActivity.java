package com.bq2015.myview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bq2015.myview.adapter.RecyclerViewAdapters;
import com.bq2015.myview.adapter.core.OnItemChildClickListener;
import com.bq2015.myview.bean.ActivityInfo;
import com.bq2015.myview.refreshlayout.BQRefreshLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/************************************************************
 * Author:  fangqq
 * Description:
 * Date:2016/5/31
 ************************************************************/
public class WidgetActivity extends Activity {
    @InjectView(R.id.widget_activity_rv)
    RecyclerView mRecyclerView;
    @InjectView(R.id.widget_activity_rl)
    BQRefreshLayout mRefreshLayout;
    private RecyclerViewAdapters mAdapters;
    private List<ActivityInfo> mActivityInfos = getActivityIofos();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_main);
        ButterKnife.inject(this);
        initData();
        initListener();
    }

    /**
     * 构建数据集合
     * @return
     */
    public List<ActivityInfo> getActivityIofos() {
        ActivityInfo[] activityInfos = new ActivityInfo[]{
                new ActivityInfo("广告轮播图", "自定义控件", AdvertiseViewActivity.class),
                new ActivityInfo("自定滑动开关", "自定义控件", SwitchToggleViewActivity.class),
                new ActivityInfo("自定义ImageView", "自定义控件", IamgeViewActivity.class)
        };
        return Arrays.asList(activityInfos);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mAdapters = new RecyclerViewAdapters(mRecyclerView);
        mAdapters.setDatas(mActivityInfos);
        mRecyclerView.setAdapter(mAdapters);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 注册事件集
     */
    private void initListener() {
        mAdapters.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                Intent intent = new Intent(WidgetActivity.this, mActivityInfos.get(position).activityClass);
                startActivity(intent);
            }
        });
    }
}
