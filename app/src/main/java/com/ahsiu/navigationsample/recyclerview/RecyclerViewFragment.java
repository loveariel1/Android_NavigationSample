package com.ahsiu.navigationsample.recyclerview;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.ahsiu.navigationsample.ui.CategoryActivity;
import com.ahsiu.navigationsample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewFragment extends Fragment{

    @BindView (R.id.refreshlayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView (R.id.recyclerview) RecyclerView mRecyclerView;

    private final String TAG = this.getClass().getName();

    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();
    private Horizontal_RecycleViewAdapter mHListAdapter;
    private Vertical_RecyclerViewAdapter mVListAdapter;
    private Grid_RecycleViewAdapter mGridAdapter;

    private Handler handler = new Handler();

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;

    private int type = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_page_content, null);
        ButterKnife.bind(this, view);
        init(getActivity());
        changeLayout(getActivity());
        return view;
    }

    public void changeLayout(Context context){
        switch (type){
            case 0:
                type = 1;
                break;
            case 1:
                type = 2;
                break;
            case 2:
                type = 0;
                break;
        }
        init(context);
    }

    private void init(Context context){
        switch (type){
            case 0:
                setHListAdapter(context);
                break;
            case 1:
                setVListAdapter(context);
                break;
            case 2:
                setGridAdapter(context);
                break;
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
                Intent intent = new Intent();
                intent.setClass(context, CategoryActivity.class);
                context.startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, "onItemLongClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVListAdapter(final Context context){
        mVListAdapter = new Vertical_RecyclerViewAdapter(context, data);
        mVListAdapter.setOnItemClickListener(new Vertical_RecyclerViewAdapter.OnItemClickListener() {
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
                Intent intent = new Intent();
                intent.setClass(context, CategoryActivity.class);
                context.startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

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
        switch (type){
            case 0:
                linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setAdapter(mHListAdapter);
                break;
            case 1:
                linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setAdapter(mVListAdapter);
                break;
            case 2:
                gridLayoutManager = new GridLayoutManager(context, 3, GridLayout.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(mGridAdapter);
                break;
        }

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                switch (type){
                    case 0:
                        if (linearLayoutManager.findLastVisibleItemPosition() + 1 == mHListAdapter.getItemCount()) {

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
                        break;
                    case 1:
                        if (linearLayoutManager.findLastVisibleItemPosition() + 1 == mVListAdapter.getItemCount()) {

                            boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                            if (isRefreshing) {
                                mVListAdapter.notifyItemRemoved(mVListAdapter.getItemCount());
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
                        break;
                    case 2:
                        if (gridLayoutManager.findLastVisibleItemPosition() + 1 == mGridAdapter.getItemCount()) {

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
                        break;
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
        switch (type) {
            case 0:
                mHListAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
                mHListAdapter.notifyItemRemoved(mHListAdapter.getItemCount());
                break;
            case 1:
                mVListAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
                mVListAdapter.notifyItemRemoved(mVListAdapter.getItemCount());
                break;
            case 2:
                mGridAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
                mGridAdapter.notifyItemRemoved(mGridAdapter.getItemCount());
                break;
        }
    }
}
