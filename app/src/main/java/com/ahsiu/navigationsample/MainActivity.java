package com.ahsiu.navigationsample;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.ahsiu.navigationsample.epoxy.Vertical_Epoxy_ListPage;
import com.ahsiu.navigationsample.recyclerview.FragmentAdapter;
import com.ahsiu.navigationsample.recyclerview.Horizontal_ListPage;
import com.ahsiu.navigationsample.recyclerview.RecyclerViewFragment;
import com.ahsiu.navigationsample.recyclerview.Vertical_ListPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.view.MenuItemCompat.setOnActionExpandListener;
import static com.ahsiu.navigationsample.R.id.bottomSheetHeading;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView (R.id.toolbar) Toolbar mToolbar;
    @BindView (R.id.fab) FloatingActionButton mFab;
    @BindView (R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView (R.id.nav_view) NavigationView mNavigationView;

    @BindView (R.id.tabs) TabLayout mTablayout;
    @BindView (R.id.viewpager) ViewPager mViewPager;

    private List<Fragment> mFragmentList;

    BottomSheetBehavior mBottomSheetBehavior;

    RecyclerViewFragment mRecyclerViewFragment;

    SearchView mSearchView;
    MenuItem mMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        }).show();

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        initData();
        initView();
        initBottomSheet();
        selectPage(1);
    }

    private void initBottomSheet(){
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        mRecyclerViewFragment = new RecyclerViewFragment();
        mFragmentList.add(mRecyclerViewFragment);
    }

    private void initView() {
        mTablayout.addTab(mTablayout.newTab().setText("RecyclerView"));
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragmentList));

        initListener();
    }

    void selectPage(int pageIndex){
        mTablayout.setScrollPosition(pageIndex,0f,true);
        mViewPager.setCurrentItem(pageIndex);
    }

    private void initListener() {
        mTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        mMenuItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView = (SearchView) mMenuItem.getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //設定 Search View Expand 的寬度
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        //顯示 執行Search 的按鈕
        mSearchView.setSubmitButtonEnabled(false);
        //監聽 Search View input or Submit 的行為
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "onQueryTextSubmit:" + query, Toast.LENGTH_SHORT).show();
                mMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "onQueryTextChange:" + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mSearchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {

                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                return false;
            }
        });

        //當Search Dialog消失的時候,會觸發OnDismiss Callback
        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(MainActivity.this, "Search Dialog OnDismiss", Toast.LENGTH_SHORT).show();
            }
        });


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;

        } else if (id == R.id.action_player){
            openMusicPlayer();
            return true;
        } else if (id == R.id.action_refresh){
//            horizontal_listPage.changeLayout(MainActivity.this);
            mRecyclerViewFragment.changeLayout(MainActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openMusicPlayer(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MusicPlayer.class);
        startActivity(intent);
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

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
