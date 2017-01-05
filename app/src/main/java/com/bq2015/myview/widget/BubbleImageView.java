package com.bq2015.myview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bq2015.myview.R;

/**
 * Created by Kylin on 2016/7/24.
 */
public class BubbleImageView extends ImageView{

    private float r = 10;
    private float textSize = 10;
    private String text = "有";

    public BubbleImageView(Context context) {
        super(context);
    }

    public BubbleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //根据属性初始化
        initAttrs(attrs);
    }


    /**
     * 根据属性初始化
     * @param attrs 属性
     */
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BubbleImageView);
        r = typedArray.getDimension(R.styleable.BubbleImageView_r, 10);
        textSize = typedArray.getDimension(R.styleable.BubbleImageView_text_size, 10);
        text = typedArray.getString(R.styleable.BubbleImageView_text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        canvas.drawCircle(getWidth() - r, r, r, paint);
        paint.setTextSize(textSize);
        paint.setColor(Color.WHITE);
        float[] widths = new float[String.valueOf(text).length()];
        paint.getTextWidths(String.valueOf(text), widths);
        float length = 0;
        float height = paint.getTextSize() / 2;
        for (float width : widths) {
            length = width + length;
        }
        length = length / 2;
        //这里是画数字
        canvas.drawText(String.valueOf(text), getWidth() - r - length, r + height, paint);
        super.onDraw(canvas);
    }
}
