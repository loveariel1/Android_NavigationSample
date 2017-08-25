package com.ahsiu.navigationsample;


import android.support.v4.app.Fragment;

import java.util.List;


public class TabLayoutContent {

    List<Fragment> mFragmentList;
    List<String> mTabTitleList;

    public TabLayoutContent(List<Fragment> fragments, List<String> tabTitles){
        this.mFragmentList = fragments;
        this.mTabTitleList = tabTitles;
    }

    public List<Fragment> getFragment(){
        return mFragmentList;
    }

    public List<String> getTabTitle(){
        return mTabTitleList;
    }

}
