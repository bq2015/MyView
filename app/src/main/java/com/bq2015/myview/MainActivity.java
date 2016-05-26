package com.bq2015.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bq2015.myview.adapter.RecyclerViewAdapter;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 主页
 * Created by Kylin on 2016/5/27.
 */
public class MainActivity extends Activity {
    @InjectView(R.id.main_activity_tv)
    TextView mIvTitle;
    @InjectView(R.id.main_activity_recyclerview)
    RecyclerView mRecyclerview;

    private final ActivityInfo[] activitys = getActivitys();

    private ActivityInfo[] getActivitys() {
        ActivityInfo[] activityInfos = {new ActivityInfo("","",AdvertiseViewActivity.class)};
        return activityInfos;
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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(activitys);
        mRecyclerview.setAdapter(adapter);
    }

    public static class ActivityInfo {
       public String title;
       public String detail;
        public Class<? extends Activity> activityClass;

        public ActivityInfo(String title, String detail,Class<? extends Activity> activityClass) {
            this.title = title;
            this.detail = detail;
            this.activityClass = activityClass;

        }
    }


}
