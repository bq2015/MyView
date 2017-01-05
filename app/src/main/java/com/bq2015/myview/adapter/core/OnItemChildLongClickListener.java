


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