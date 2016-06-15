package com.bq2015.myview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/************************************************************
 * Author:  FangQiQuan
 * Description:
 * Date:2016/6/15
 ************************************************************/
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * startActivity
     */
    final public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
