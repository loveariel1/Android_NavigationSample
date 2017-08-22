package com.ahsiu.navigationsample.epoxy;

import android.content.Context;
import android.view.View;

import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.TypedEpoxyController;

import com.ahsiu.navigationsample.epoxy.ListItemModel.ListItemHolder;

import java.util.List;

/**
 * Created by jackie780919 on 2017/8/22.
 */

public class ListController extends TypedEpoxyController<List<Comment>> {

    private final Context context;

    public ListController(Context context){
        this.context = context;
    }

    @Override
    protected void buildModels(List<Comment> data) {

        for(Comment comment : data) {
            new ListItemModel_()
                    .id(comment.getId())
                    .comment(comment.getComment())
                    .clickListener(new OnModelClickListener<ListItemModel_, ListItemHolder>() {

                        @Override
                        public void onClick(ListItemModel_ model, ListItemModel.ListItemHolder parentView, View clickedView, int position) {

                        }
                    })
                    .addTo(this);
        }
    }
}
