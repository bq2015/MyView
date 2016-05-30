
/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.bq2015.myview.adapter.core;

import android.view.View;
import android.view.ViewGroup;

/**
 * for RecyclerView item
 */
public interface OnRVItemLongClickListener {
    boolean onRVItemLongClick(ViewGroup parent, View itemView, int position);
}