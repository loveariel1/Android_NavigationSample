package com.ahsiu.navigationsample.epoxy;

import android.support.annotation.CallSuper;
import android.view.View;

import com.airbnb.epoxy.EpoxyHolder;

import butterknife.ButterKnife;

/**
 * Created by jackie780919 on 2017/8/22.
 */

public abstract class BaseEpoxyHolder extends EpoxyHolder{
    @CallSuper
    @Override
    protected void bindView(View itemView){
        ButterKnife.bind(this, itemView);
    }

}
