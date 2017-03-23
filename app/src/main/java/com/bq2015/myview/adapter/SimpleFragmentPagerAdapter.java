package com.bq2015.myview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bq2015.myview.R;
import com.bq2015.myview.fragment.PageFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3", "tab2", "tab3", "tab2",
            "tab3", "tab2", "tab3", "tab2", "tab3", "tab2", "tab3",
            "tab3", "tab2", "tab3", "tab2", "tab3", "tab2", "tab3",
            "tab3", "tab2", "tab3", "tab2", "tab3", "tab2", "tab3",
            "tab3", "tab2", "tab3", "tab2", "tab3", "tab2", "tab3"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        PAGE_COUNT = tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(R.mipmap.ic_launcher);
        return view;
    }
}