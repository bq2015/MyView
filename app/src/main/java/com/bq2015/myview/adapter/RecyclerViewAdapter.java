package com.bq2015.myview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bq2015.myview.MainActivity;
import com.bq2015.myview.R;

import java.util.List;

/**
 * Recycler适配器
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    private MainActivity.ActivityInfo[] mData;

    public RecyclerViewAdapter(MainActivity.ActivityInfo[] data) {
        mData = data;
    }

    /**
     * 创建视图
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(new TextView(parent.getContext()));
    }

    /**
     * 向示图绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        //绑定数据
        holder.mTvitem.setText(mData[position].title);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.length : 0;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvitem;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTvitem = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    }