package com.ahsiu.navigationsample.ui;

import android.os.Bundle;

import com.ahsiu.navigationsample.R;
import com.ahsiu.navigationsample.model.ViewPagerInfo;
import com.ahsiu.navigationsample.recyclerview.RecyclerViewFragment;

import java.util.ArrayList;

public class CategoryActivity extends BaseTabActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    public int getMenuLayoutId() {
        return R.menu.category_main;
    }

    @Override
    public void setRefreshBtn() {
        mRecyclerViewFragment.changeLayout(this);
    }

    @Override
    public ArrayList<ViewPagerInfo> getViewPagerInfo() {
        ArrayList<ViewPagerInfo> list = new ArrayList<>();

        mRecyclerViewFragment = new RecyclerViewFragment();

        list.add(new ViewPagerInfo("RecyclerView", mRecyclerViewFragment));
        return list;
    }

    RecyclerViewFragment mRecyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
