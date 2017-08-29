package com.ahsiu.navigationsample.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.ahsiu.navigationsample.R;
import com.ahsiu.navigationsample.model.ViewPagerInfo;
import com.ahsiu.navigationsample.recyclerview.FragmentAdapter;

import java.util.ArrayList;

public abstract class BaseTabActivity extends BaseActivity {

    TabLayout mTablayout;
    ViewPager mViewPager;

    public abstract ArrayList<ViewPagerInfo> getViewPagerInfo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addViewPager();

        mTablayout = (TabLayout) findViewById(R.id.base_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.base_viewpager);
        mTablayout.setVisibility(View.VISIBLE);

        initView();
    }

    public void addViewPager() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.viewpager, null);
        mFrameLayout.addView(contentView, 0);
    }

    private void initView() {

        if(getViewPagerInfo() == null){
            return;
        }

        for(int i = 0; i < getViewPagerInfo().size(); i++){
            mTablayout.addTab(mTablayout.newTab().setText(getViewPagerInfo().get(i).getTitle()));
        }

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), getViewPagerInfo()));

        mTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
    }

    public void selectPage(int pageIndex){
        mTablayout.setScrollPosition(pageIndex,0f,true);
        mViewPager.setCurrentItem(pageIndex);
    }
}
