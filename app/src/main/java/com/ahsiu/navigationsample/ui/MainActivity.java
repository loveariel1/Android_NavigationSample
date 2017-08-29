package com.ahsiu.navigationsample.ui;


import android.os.Bundle;

import com.ahsiu.navigationsample.R;
import com.ahsiu.navigationsample.epoxy.EpoxyFragment;
import com.ahsiu.navigationsample.model.ViewPagerInfo;
import com.ahsiu.navigationsample.recyclerview.RecyclerViewFragment;

import java.util.ArrayList;


public class MainActivity extends BaseTabActivity {


    @Override
    public ArrayList<ViewPagerInfo> getViewPagerInfo() {

        ArrayList<ViewPagerInfo> list = new ArrayList<>();
        mRecyclerViewFragment = new RecyclerViewFragment();
        mEpoxyFragment = new EpoxyFragment();

        list.add(new ViewPagerInfo("RecyclerView", mRecyclerViewFragment));
        list.add(new ViewPagerInfo("Epoxy", mEpoxyFragment));
        return list;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getMenuLayoutId() {
        return 0;
    }

    @Override
    public void setRefreshBtn() {
        mRecyclerViewFragment.changeLayout(this);
    }

    RecyclerViewFragment mRecyclerViewFragment;
    EpoxyFragment mEpoxyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
