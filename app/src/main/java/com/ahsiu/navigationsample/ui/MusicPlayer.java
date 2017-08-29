package com.ahsiu.navigationsample.ui;

import android.os.Bundle;

import com.ahsiu.navigationsample.R;
import com.ahsiu.navigationsample.ui.BaseActivity;

public class MusicPlayer extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_musicplayer;
    }

    @Override
    public int getMenuLayoutId() {
        return 0;
    }

    @Override
    public void setRefreshBtn() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
