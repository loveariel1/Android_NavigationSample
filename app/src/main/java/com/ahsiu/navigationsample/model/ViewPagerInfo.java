package com.ahsiu.navigationsample.model;


import android.support.v4.app.Fragment;

public class ViewPagerInfo {

    String mTitle;
    Fragment mFragment;

    public ViewPagerInfo(String title, Fragment fragment){
        this.mTitle = title;
        this.mFragment = fragment;
    }

    public String getTitle(){
        return mTitle;
    }

    public Fragment getFragment(){
        return mFragment;
    }

}
