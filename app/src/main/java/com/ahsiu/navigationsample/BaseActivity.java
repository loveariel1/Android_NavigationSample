package com.ahsiu.navigationsample;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ahsiu.navigationsample.recyclerview.FragmentAdapter;
import com.ahsiu.navigationsample.recyclerview.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public abstract int getLayoutId();

    public abstract int getLayoutStyle();

    public abstract TabLayoutContent getTabLayoutContent();

    public final int NORMAL_LAYOUT = 0;
    public final int TAB_LAYOUT = 1;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar mToolbar;
    TabLayout mTablayout;
    ViewPager mViewPager;
    NestedScrollView mNestedScrollView;

    MenuItem mMenuItem;
    SearchView mSearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        findView();
        setView();
    }

    protected void findView(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.base_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.base_nav_view);
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        mTablayout = (TabLayout) findViewById(R.id.base_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.base_viewpager);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.base_nestedscrollview);

        //from custom activity
        addContentView(getLayoutId());
        ButterKnife.bind(this);
        setLayoutStyle(getLayoutStyle());
    }

    protected void setLayoutStyle(int layoutStyle){
        switch (layoutStyle){
            case TAB_LAYOUT:
                initView();
                break;
            default:
                break;
        }
    }

    private void initView() {
        for(int i = 0; i < getTabLayoutContent().getTabTitle().size(); i++){
            mTablayout.addTab(mTablayout.newTab().setText(getTabLayoutContent().getTabTitle().get(i)));
        }

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), getTabLayoutContent().getFragment()));

        mTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
    }

    public void selectPage(int pageIndex){
        mTablayout.setScrollPosition(pageIndex,0f,true);
        mViewPager.setCurrentItem(pageIndex);
    }

    protected void setView(){
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void addContentView(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        mDrawerLayout.addView(contentView, 0);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        mMenuItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) mMenuItem.getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;

        } else if (id == R.id.action_player){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
