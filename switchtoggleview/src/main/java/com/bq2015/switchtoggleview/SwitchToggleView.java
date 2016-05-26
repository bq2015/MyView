package com.bq2015.switchtoggleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kylin on 2016/5/25.
 */
public class SwitchToggleView extends View {

    private Bitmap mBackgroundBitmap;
    private Bitmap mSlideBitmap;
    private int mMaxLeft;
    private Paint mPaint;
    private float mLeft;
    private float mDownX;
    private float mMinLeft;
    private OnSwitchListener mListener;
    private boolean isOpen;

    public SwitchToggleView(Context context) {
        super(context, null);
    }

    public SwitchToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //滑块背景bitmap
        mBackgroundBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.switch_background);
        //滑块bitmap
        mSlideBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.slide_button_background);

        //滑块能够滑到最右边的距离
        mMaxLeft = mBackgroundBitmap.getWidth() - mSlideBitmap.getWidth();

        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //把前景图片的高度设置为switchtoggleview的高度
        setMeasuredDimension(mBackgroundBitmap.getWidth(), mBackgroundBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1.画前景
        canvas.drawBitmap(mBackgroundBitmap, 0, 0, mPaint);
        //2.画开关
        canvas.drawBitmap(mSlideBitmap, mLeft, 0, mPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mDownX = event.getX();
                mLeft = mDownX - mSlideBitmap.getWidth() / 2;
                break;
            case MotionEvent.ACTION_UP:
                //背景中线
                int middle = mBackgroundBitmap.getWidth() / 2;
                float upX = event.getX();
                if (upX < middle) {
                    mLeft = mMinLeft;
                    //滑动开关关闭
                    if (mListener != null && isOpen) {
                        mListener.onSwitch(false);
                        isOpen = false;
                    }
                } else {
                    //大于或者等于背景中线位置, 滚到最右边
                    mLeft = mMaxLeft;
                    //滑动开关打开
                    if (mListener != null && !isOpen) {
                        mListener.onSwitch(true);
                        isOpen = true;
                    }
                }
                break;
        }


        //如果小于最小值
        if (mLeft < mMinLeft) {
            mLeft = mMinLeft;
        } else if (mLeft > mMaxLeft) {
            mLeft = mMaxLeft;

        }


        invalidate();//触发重新绘制
        return true;//消费
    }

    /**
     * 定义接口,侦听开关状态
     */
    public interface OnSwitchListener {
        void onSwitch(boolean isOpen);
    }

    /**
     * 对外提供设置传递接口实例的方法
     */
    public void setOnSwitchListener(OnSwitchListener listener){
        mListener = listener;
    }
}

