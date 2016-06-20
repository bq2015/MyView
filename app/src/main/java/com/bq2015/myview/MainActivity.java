package com.bq2015.myview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bq2015.myview.activity.AnimActivity;
import com.bq2015.myview.activity.BaseActivity;
import com.bq2015.myview.adapter.RecyclerViewAdapters;
import com.bq2015.myview.adapter.core.OnRVItemClickListener;
import com.bq2015.myview.bean.ActivityInfo;
import com.bq2015.myview.refreshlayout.BQMoocStyleRefreshViewHolder;
import com.bq2015.myview.refreshlayout.BQRefreshLayout;
import com.bq2015.myview.utils.ThreadUtil;
import com.bq2015.myview.widget.TestActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 主页
 * Created by Kylin on 2016/5/27.
 */
public class MainActivity extends BaseActivity {


    private final ActivityInfo[] mActivityInfos = getActivitys();
    @BindView(R.id.rv_main)
    RecyclerView mRvMain;
    @BindView(R.id.rl_main)
    BQRefreshLayout mRlMain;

    private RecyclerViewAdapters mMainAdapter;
    private List<ActivityInfo> mInfos;

    private ActivityInfo[] getActivitys() {
        ActivityInfo[] mActivityInfos = new ActivityInfo[]{
                new ActivityInfo("Widget", "一些自定义的控件", WidgetActivity.class),
                new ActivityInfo("Animation", "android动画", AnimActivity.class),
                new ActivityInfo("Test", "快速测试", TestActivity.class)
        };
        return mActivityInfos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }


    /**
     * 初始化
     */
    private void init() {
        mMainAdapter = new RecyclerViewAdapters(mRvMain);
        mInfos = Arrays.asList(mActivityInfos);
        mMainAdapter.setDatas(mInfos);

        /**
         * 条目点击事件
         */
      mMainAdapter.setOnRVItemClickListener(new OnRVItemClickListener() {
          @Override
          public void onRVItemClick(ViewGroup parent, View itemView, int position) {
              readyGo(mMainAdapter.getItem(position).activityClass);
          }
      });

        mode1();

        mRlMain.setDelegate(new BQRefreshLayout.RefreshLayoutDelegate() {
            @Override
            public void onRefreshLayoutBeginRefreshing(BQRefreshLayout refreshLayout) {
                //模拟延迟效果
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {

                        mMainAdapter.setDatas(mInfos);
                        mRlMain.endRefreshing();
                    }
                }, 2000);
            }

            @Override
            public boolean onRefreshLayoutBeginLoadingMore(BQRefreshLayout refreshLayout) {
                return false;
            }
        });

        mRvMain.setLayoutManager(new LinearLayoutManager(this));
        mRvMain.setAdapter(mMainAdapter);
    }


    private void mode1() {
        BQMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BQMoocStyleRefreshViewHolder(this, true);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
        mRlMain.setRefreshViewHolder(moocStyleRefreshViewHolder);
    }


}
