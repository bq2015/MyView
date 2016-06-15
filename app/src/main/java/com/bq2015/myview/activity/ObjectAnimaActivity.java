package com.bq2015.myview.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bq2015.myview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/************************************************************
 * Author:  FangQiQuan
 * Description:
 * Date:2016/6/15
 ************************************************************/
public class ObjectAnimaActivity extends BaseActivity {
    @BindView(R.id.tv_valueanima)
    TextView mTvValueanima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanimation);
        ButterKnife.bind(this);
        playtranslationX();
        playAnimaSet();
    }

    /**
     * 动画集
     */

    private void playAnimaSet() {
        mTvValueanima.setText(R.string.valueanimation);
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTvValueanima, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTvValueanima, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut  = ObjectAnimator.ofFloat(mTvValueanima, "alpha", 0f, 360f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(moveIn).with(rotate).after(fadeInOut);
        animatorSet.setDuration(3000);
        animatorSet.start();
        /**
         * 监听动画
         */
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(ObjectAnimaActivity.this, "animation is complete!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * X坐标平移
     */
    private void playtranslationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTvValueanima, "translationX",-500f);
        animator.setDuration(3000);
        animator.start();
    }
}
