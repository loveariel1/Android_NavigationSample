package com.ahsiu.navigationsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jackie780919 on 2017/8/18.
 */

public class PageTwo extends PageView {

    public PageTwo(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.page_content, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Page two");
        addView(view);
    }

    @Override
    public void refresh() {

    }
}
