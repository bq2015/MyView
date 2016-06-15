package com.bq2015.myview.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.widget.TextView;

import com.bq2015.myview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/************************************************************
 * Author:  FangQiQuan
 * Description:
 * Date:2016/6/15
 ************************************************************/
public class XMLAnimaActivity extends BaseActivity {
    @BindView(R.id.tv_valueanima)
    TextView mTvValueanima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanimation);
        ButterKnife.bind(this);
        playByXML();
    }

    private void playByXML() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.anim_file);
        animator.setTarget(mTvValueanima);
        animator.start();
    }

}
