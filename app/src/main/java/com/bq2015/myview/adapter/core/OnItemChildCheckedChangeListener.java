

package com.bq2015.myview.adapter.core;

import android.view.ViewGroup;
import android.widget.CompoundButton;

/**
 * for recyclerView and adapterView subItem
 */
public interface OnItemChildCheckedChangeListener {
    void onItemChildCheckedChanged(ViewGroup parent, CompoundButton childView, int position, boolean isChecked);
}