package com.ahsiu.navigationsample.recyclerview;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ahsiu.navigationsample.PageView;
import com.ahsiu.navigationsample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Vertical_ListPage extends PageView {

    @BindView (R.id.refreshlayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView (R.id.recyclerview) RecyclerView mRecyclerView;

    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();
    private Vertical_RecyclerViewAdapter mAdapter;
    private Handler handler = new Handler();

    public Vertical_ListPage(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.listview_page_content, null);
        ButterKnife.bind(this, view);
        addView(view);

        mAdapter = new Vertical_RecyclerViewAdapter(context, data);
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {

                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
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
        });

        mAdapter.setOnItemClickListener(new Vertical_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, "onItemLongClick", Toast.LENGTH_SHORT).show();
            }
        });

        initData();
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
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

}
