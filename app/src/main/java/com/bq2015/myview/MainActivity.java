package com.bq2015.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bq2015.myview.activity.AnimActivity;
import com.bq2015.myview.activity.BaseActivity;
import com.bq2015.myview.adapter.RecyclerViewAdapters;
import com.bq2015.myview.adapter.core.OnRVItemClickListener;
import com.bq2015.myview.bean.ActivityInfo;
import com.bq2015.myview.refreshlayout.BQMeiTuanRefreshViewHolder;
import com.bq2015.myview.refreshlayout.BQMoocStyleRefreshViewHolder;
import com.bq2015.myview.refreshlayout.BQRefreshLayout;
import com.bq2015.myview.utils.ThreadUtil;
import com.bq2015.myview.widget.FlowTagLayout;
import com.bq2015.myview.widget.TestActivity;

import java.util.ArrayList;
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
    List<String> mlist = new ArrayList<>();

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

        for (int i = 0; i < 10; i++) {
            mlist.add("标签" + i);

        }

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

//        mode1();
        mode3();

        //填充标签块
        View headview = View.inflate(this, R.layout.tab_flow, null);
        FlowTagLayout ftlView = (FlowTagLayout) headview.findViewById(R.id.activity_ftl);
        //设置单选
        ftlView.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        ftlView.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Toast.makeText(MainActivity.this, "第"+position+1+"个标签被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        //设置数据
        mRlMain.setCustomHeaderView(headview, true);//true头部可隐藏
        TagAdapter tagAdapter = new TagAdapter();
        ftlView.setAdapter(tagAdapter);
        tagAdapter.notifyDataSetChanged();
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

    private void mode3() {
        BQMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BQMeiTuanRefreshViewHolder(this, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);

        mRlMain.setRefreshViewHolder(meiTuanRefreshViewHolder);
    }




    class TagAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mlist != null ? mlist.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(MainActivity.this, R.layout.lab, null);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.lab_tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv.setText(mlist.get(position));
            return convertView;
        }



        private  class ViewHolder {
            TextView tv;
        }
    }






}
