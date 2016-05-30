package com.bq2015.myview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.bq2015.myview.R;

/**
 * Created by Kylin on 2016/5/31.
 */
public class SimpleImageView extends View {

    private Paint mPaint;
    private TypedArray array;

    public SimpleImageView(Context context) {
        this(context, null);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //根据属性初始化
        initAttrs(attrs);

        //初始化画笔
        mPaint = new Paint();
        //消除锯齿
        mPaint.setAntiAlias(true);

    }

    private void initAttrs(AttributeSet attrs) {
        // TODO: 2016/5/31  初始化
        if (attrs != null) {
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                //根据图片Id获取到Drawable对像
                array.getDrawable(R.styleable.SimpleImageView_src);

                //测量Drawable对像的宽、高
                mesureDrawable();

            }finally {
                if (array != null) {
                    array.recycle();
                    array = null;
                }

            }

        } else {

        }
    }

    private void mesureDrawable() {
        // TODO: 2016/5/31 测量
    }


}
