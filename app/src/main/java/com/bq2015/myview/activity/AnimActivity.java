package com.bq2015.myview.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bq2015.myview.R;
import com.bq2015.myview.adapter.RecyclerViewAdapters;
import com.bq2015.myview.adapter.core.OnRVItemClickListener;
import com.bq2015.myview.bean.ActivityInfo;
import com.bq2015.myview.refreshlayout.BQMoocStyleRefreshViewHolder;
import com.bq2015.myview.refreshlayout.BQRefreshLayout;
import com.bq2015.myview.utils.ThreadUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/************************************************************
 * Author:  FangQiQuan
 * Description:
 * Date:2016/6/15
 ************************************************************/
public class AnimActivity extends BaseActivity implements BQRefreshLayout.RefreshLayoutDelegate {

    List<ActivityInfo> mActivityInfos = null;

    @BindView(R.id.rv_anima)
    RecyclerView mRvAnima;
    @BindView(R.id.rl_anima)
    BQRefreshLayout mRlAnima;

    private RecyclerViewAdapters mAnimaAdapter;


    private ActivityInfo[] getActivitys() {
        ActivityInfo[] mActivityInfos = new ActivityInfo[]{
                new ActivityInfo("ValueAnimator", "android属性动画-Value", ValueAnimaActivity.class),
                new ActivityInfo("ObjectAnimator", "android属性动画-Object", ObjectAnimaActivity.class),
                new ActivityInfo("AnimatorByXML", "android属性动画-XML", XMLAnimaActivity.class)
        };
        return mActivityInfos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        init();

    }


    private void init() {
        mode1();
        mActivityInfos = Arrays.asList(getActivitys());
        mAnimaAdapter = new RecyclerViewAdapters(mRvAnima);
        mAnimaAdapter.setDatas(mActivityInfos);
        mRlAnima.setDelegate(this);
        mRvAnima.setLayoutManager(new LinearLayoutManager(this));
        mRvAnima.setAdapter(mAnimaAdapter);

        mAnimaAdapter.setOnRVItemClickListener(new OnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                readyGo(mAnimaAdapter.getItem(position).activityClass);
            }
        });
    }

    /**
     * 设置刷新动画
     */
    private void mode1() {
        BQMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BQMoocStyleRefreshViewHolder(this, true);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
        mRlAnima.setRefreshViewHolder(moocStyleRefreshViewHolder);
    }

    @Override
    public void onRefreshLayoutBeginRefreshing(BQRefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRlAnima.endRefreshing();
            }
        });
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(BQRefreshLayout refreshLayout) {
        return false;
    }
}
