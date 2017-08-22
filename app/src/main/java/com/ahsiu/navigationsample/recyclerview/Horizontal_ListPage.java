package com.ahsiu.navigationsample.recyclerview;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.ahsiu.navigationsample.PageView;
import com.ahsiu.navigationsample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Horizontal_ListPage extends PageView {

    @BindView (R.id.horizontal_refreshlayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView (R.id.horizontal_recyclerview) RecyclerView mRecyclerView;

    private final String TAG = this.getClass().getName();

    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();
    private Horizontal_RecycleViewAdapter mHListAdapter;
    private Grid_RecycleViewAdapter mGridAdapter;
    private Handler handler = new Handler();

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;

    private int type = 1;

    public Horizontal_ListPage(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.listview_horizontal_page_content, null);
        ButterKnife.bind(this, view);
        addView(view);
        if(type == 0){
            setHListAdapter(context);
        }else{
            setGridAdapter(context);
        }
        setSwipeRefreshLayout();
        setRecyclerView(context);
        initData();
    }

    private void setHListAdapter(final Context context){
        mHListAdapter = new Horizontal_RecycleViewAdapter(context, data);
        mHListAdapter.setOnItemClickListener(new Horizontal_RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, "onItemLongClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setGridAdapter(final Context context){
        mGridAdapter = new Grid_RecycleViewAdapter(context, data);
        mGridAdapter.setOnItemClickListener(new Grid_RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, "onItemLongClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSwipeRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        getData();
                    }
                }, 2000);
            }
        });
    }

    private void setRecyclerView(Context context){
        if(type == 0){
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(mHListAdapter);
        }else{
            gridLayoutManager = new GridLayoutManager(context, 2, GridLayout.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setAdapter(mGridAdapter);
        }

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i(TAG, "newState:" + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(TAG, "dx:" + dx + "  dy:" + dy);

                if(type == 0){
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition + 1 == mHListAdapter.getItemCount()) {

                        boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                        if (isRefreshing) {
                            mHListAdapter.notifyItemRemoved(mHListAdapter.getItemCount());
                            return;
                        }
                        if (!isLoading) {
                            isLoading = true;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getData();
                                    isLoading = false;
                                }
                            }, 1000);
                        }
                    }
                }else{
                    int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition + 1 == mGridAdapter.getItemCount()) {

                        boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                        if (isRefreshing) {
                            mGridAdapter.notifyItemRemoved(mGridAdapter.getItemCount());
                            return;
                        }
                        if (!isLoading) {
                            isLoading = true;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getData();
                                    isLoading = false;
                                }
                            }, 1000);
                        }
                    }
                }


            }
        });
    }

    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);

    }

    private void getData() {
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        if(type == 0){
            mHListAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
            mHListAdapter.notifyItemRemoved(mHListAdapter.getItemCount());
        }else{
            mGridAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
            mGridAdapter.notifyItemRemoved(mGridAdapter.getItemCount());
        }

    }

}
