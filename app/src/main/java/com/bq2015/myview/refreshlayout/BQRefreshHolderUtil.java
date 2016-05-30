/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.myview.refreshlayout;

import android.content.Context;

import com.bq2015.myview.R;


/**
 * @author YaoWeihui on 2016/4/26.
 */
public class BQRefreshHolderUtil {
    public static BQRefreshViewHolder getHolder(Context ctx) {
        BQMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BQMeiTuanRefreshViewHolder(ctx, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        return meiTuanRefreshViewHolder;
    }

    public static BQRefreshViewHolder getHolder(Context ctx, boolean loadMoreEnable) {
        BQMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BQMeiTuanRefreshViewHolder(ctx, loadMoreEnable);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        return meiTuanRefreshViewHolder;
    }
}
