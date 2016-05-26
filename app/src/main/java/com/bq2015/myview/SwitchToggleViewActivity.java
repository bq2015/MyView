package com.bq2015.myview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.bq2015.switchtoggleview.SwitchToggleView;

/**
 * Created by Kylin on 2016/5/25.
 */
public class SwitchToggleViewActivity extends Activity implements SwitchToggleView.OnSwitchListener {

    private SwitchToggleView mSwitchToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switchtoggleview);
        mSwitchToggleView = (SwitchToggleView) findViewById(R.id.switchtoggle_view);
        mSwitchToggleView.setOnSwitchListener(this);
    }

    @Override
    public void onSwitch(boolean isOpen) {
        Toast.makeText(this,isOpen+"",Toast.LENGTH_SHORT).show();
    }
}
