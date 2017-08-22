package com.ahsiu.navigationsample.epoxy;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ahsiu.navigationsample.PageView;
import com.ahsiu.navigationsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Vertical_Epoxy_ListPage extends PageView {

    @BindView (R.id.epoxy_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.epoxy_refreshlayout) SwipeRefreshLayout mSwipeRefreshLayout;
    private ListController controller;

    public Vertical_Epoxy_ListPage(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.epoxy_page_content, null);
        ButterKnife.bind(this, view);
        addView(view);
        setUpController(context);
        setUpRecyclerView(context);
        updateController();
        setSwipeRefreshLayout();
    }

    private void setUpRecyclerView(Context context) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(controller.getAdapter());
    }

    private void setUpController(Context context) {
        controller = new ListController(context);
    }

    private void updateController() {
        List<Comment> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            result.add(new Comment(i, "Comment:" + String.valueOf(i)));
        }
        controller.setData(result);
    }

    private void setSwipeRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
}
