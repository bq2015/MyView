package com.bq2015.myview.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bq2015.myview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/************************************************************
 * Author:  FangQiQuan
 * Description:
 * Date:2016/6/15
 ************************************************************/
public class ValueAnimaActivity extends BaseActivity {
    private static final String TAG = "ValueAnimaActivity";
    @BindView(R.id.tv_valueanima)
    TextView mTvValueanima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanimation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTvValueanima.setText(R.string.valueanimation);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f,0f,1f);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.d(TAG, "animatedValue = " + animatedValue);
                mTvValueanima.setAlpha(animatedValue);
                mTvValueanima.setRotation(animatedValue);
                mTvValueanima.setScaleX(animatedValue);
                mTvValueanima.setScaleY(animatedValue);
            }
        });
        animator.start();
    }


}
