

package com.bq2015.myview.adapter;

import android.support.v7.widget.RecyclerView;

import com.bq2015.myview.R;
import com.bq2015.myview.adapter.core.RecyclerViewAdapter;
import com.bq2015.myview.adapter.core.ViewHolderHelper;
import com.bq2015.myview.bean.ActivityInfo;

/**
 * @author Ricky Yao on 2016/4/21.
 */
public class RecyclerViewAdapters extends RecyclerViewAdapter<ActivityInfo> {


    public RecyclerViewAdapters(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_normal);
    }

    @Override
    protected void fillData(ViewHolderHelper viewHolderHelper, int position, ActivityInfo model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.title);
        viewHolderHelper.setText(R.id.tv_item_normal_detail, model.detail);

    }

    /**
     * 制定item子元素点击事件/长按事件
     */
    @Override
    protected void setItemChildListener(ViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_title);
//        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_title);
    }
}