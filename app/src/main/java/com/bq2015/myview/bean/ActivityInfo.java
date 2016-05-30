package com.bq2015.myview.bean;

import android.app.Activity;

/************************************************************
 * Author:  fangqq
 * Description:
 * Date:2016/5/30
 ************************************************************/
public class ActivityInfo {
    public String title;
    public String detail;
    public Class<? extends Activity> activityClass;

    public ActivityInfo(String title, String detail, Class<? extends Activity> activityClass) {
        this.title = title;
        this.detail = detail;
        this.activityClass = activityClass;

    }
}
