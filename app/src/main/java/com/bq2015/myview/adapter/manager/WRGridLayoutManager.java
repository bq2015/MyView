

package com.bq2015.myview.adapter.manager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Created by lyao on 2016/1/19.
 */
public class WRGridLayoutManager extends GridLayoutManager {

    private int itemDecorate;
    private boolean mayFix = true;

    public WRGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    /**
     * someTime need mayFix，i don't know when happen
     *
     * @param mayFix true or false
     */
    public void setFixHeight(boolean mayFix) {
        this.mayFix = mayFix;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        final int addIndex = getOrientation() == VERTICAL ? 1 : 0;
        final int maxIndex = getOrientation() == VERTICAL ? 0 : 1;

        int[] childMeasure = new int[2];
        int[] rowMeasure = {0, 0};
        int[] rowsSized = {0, 0};

        for (int i = 0; i < state.getItemCount(); i++) {
            // Measure the child.
            measureScrapChild(recycler, i, View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED, childMeasure);

            if (getOrientation() == VERTICAL) {
                rowMeasure[1] = Math.max(rowMeasure[1], childMeasure[1]);
                rowMeasure[0] += childMeasure[0];
            } else {
                rowMeasure[0] = Math.max(rowMeasure[0], childMeasure[0]);
                rowMeasure[1] += childMeasure[1];
            }

            // When finishing the row (last item of row), adds the row dimensions to the view.
            if (i % getSpanCount() == (getSpanCount() - 1)) {
                rowsSized[addIndex] += rowMeasure[addIndex];
                rowsSized[maxIndex] = Math.max(rowsSized[maxIndex], rowMeasure[maxIndex]);
                rowMeasure[addIndex] = 0;
                rowMeasure[maxIndex] = 0;
            }
            // for example : spanCount is 3，but count is 5
            if (state.getItemCount() % getSpanCount() != 0 && i == state.getItemCount() - 1) {
                rowsSized[addIndex] += rowMeasure[addIndex];
                rowsSized[maxIndex] = Math.max(rowsSized[maxIndex], rowMeasure[maxIndex]);
                rowMeasure[addIndex] = 0;
                rowMeasure[maxIndex] = 0;
            }
        }
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                rowsSized[0] = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                rowsSized[1] = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(rowsSized[0], rowsSized[1] + itemDecorate);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        // See code at getViewForPosition(int, boolean) to see if/how this may be problematic.
        View view = recycler.getViewForPosition(position);
        // may be not use ...
        if (mayFix) super.measureChildWithMargins(view, 0, 0);
        itemDecorate = getDecoratedBottom(view);

        RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
        int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                getPaddingLeft() + getPaddingRight(), p.width);
        int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                getPaddingTop() + getPaddingBottom(), p.height);
        view.measure(childWidthSpec, childHeightSpec);
        measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
        measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin + itemDecorate;
        recycler.recycleView(view);
    }

}