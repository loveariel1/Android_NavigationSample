package com.ahsiu.navigationsample.recyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ahsiu.navigationsample.model.ViewPagerInfo;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter{

    ArrayList<ViewPagerInfo> ViewPagerInfos;

    public FragmentAdapter(FragmentManager fragmentManager, ArrayList<ViewPagerInfo> list){
        super(fragmentManager);
        this.ViewPagerInfos = list;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerInfos.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return ViewPagerInfos.size();
    }
}
