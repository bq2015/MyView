
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
 * for recyclerView and adapterView subItem
 */
public interface OnItemChildLongClickListener {
    /**
     *  set mul item,this callback may nonUseless,because subItem may no exist
     */
    boolean onItemChildLongClick(ViewGroup parent, View childView, int position);
}