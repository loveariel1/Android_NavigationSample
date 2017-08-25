package com.ahsiu.navigationsample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.ahsiu.navigationsample.recyclerview.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryActivity extends BaseActivity {

    @BindView (R.id.category_tabs) TabLayout mTablayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    public int getLayoutStyle() {
        return TAB_LAYOUT;
    }

    @Override
    public TabLayoutContent getTabLayoutContent() {
        List<Fragment> list = new ArrayList<>();
        RecyclerViewFragment mRecyclerViewFragment = new RecyclerViewFragment();
        list.add(mRecyclerViewFragment);

        ArrayList<String> titles = new ArrayList<>();
        titles.add("Test");
        return new TabLayoutContent(list, titles);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
