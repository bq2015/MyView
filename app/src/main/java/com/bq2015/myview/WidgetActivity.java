package com.bq2015.myview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bq2015.myview.activity.TableLayoutActivity;
import com.bq2015.myview.adapter.RecyclerViewAdapters;
import com.bq2015.myview.adapter.core.OnRVItemClickListener;
import com.bq2015.myview.bean.ActivityInfo;
import com.bq2015.myview.refreshlayout.BQRefreshLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/************************************************************
 * Author:  fangqq
 * Description:
 * Date:2016/5/31
 ************************************************************/
public class WidgetActivity extends Activity {
    RecyclerView mRecyclerView;
    @BindView(R.id.rv_widget)
    RecyclerView mRvWidget;
    @BindView(R.id.rl_widget)
    BQRefreshLayout mRlWidget;

    private RecyclerViewAdapters mWidgetAdapters;
    private List<ActivityInfo> mActivityInfos = getActivityIofos();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    /**
     * 构建数据集合
     *
     * @return
     */
    public List<ActivityInfo> getActivityIofos() {
        ActivityInfo[] activityInfos = new ActivityInfo[]{
                new ActivityInfo("广告轮播图", "自定义控件", AdvertiseViewActivity.class),
                new ActivityInfo("自定滑动开关", "自定义控件", SwitchToggleViewActivity.class),
                new ActivityInfo("自定义ImageView", "ImageView", IamgeViewActivity.class),
                new ActivityInfo("自定义小气泡控件", "小气泡", BubbleIamgeViewActivity.class),
                new ActivityInfo("TabLayout", "TabLayout的使用", TableLayoutActivity.class)

        };
        return Arrays.asList(activityInfos);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mWidgetAdapters = new RecyclerViewAdapters(mRvWidget);
        mWidgetAdapters.setDatas(mActivityInfos);
        mRvWidget.setLayoutManager(new LinearLayoutManager(this));
        mRvWidget.setAdapter(mWidgetAdapters);
    }

    /**
     * 注册事件集
     */
    private void initListener() {
        mWidgetAdapters.setOnRVItemClickListener(new OnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Intent intent = new Intent(WidgetActivity.this, mActivityInfos.get(position).activityClass);
                startActivity(intent);
            }
        });
    }
}
