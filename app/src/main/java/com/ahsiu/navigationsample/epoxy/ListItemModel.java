package com.ahsiu.navigationsample.epoxy;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.ahsiu.navigationsample.R;
import com.ahsiu.navigationsample.epoxy.ListItemModel.ListItemHolder;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

/**
 * Created by jackie780919 on 2017/8/22.
 */

@EpoxyModelClass(layout = R.layout.epoxy_list_item_view)
public abstract class ListItemModel extends EpoxyModelWithHolder<ListItemHolder> {
    @EpoxyAttribute String comment;
    @EpoxyAttribute(DoNotHash) OnClickListener clickListener;

    @Override
    public void bind(ListItemHolder holder){
        holder.itemView.setOnClickListener(clickListener);
        holder.commentTextView.setText(comment);
    }

    @Override
    public void unbind(ListItemHolder holder) {
        holder.itemView.setOnClickListener(null);
    }

    public static class ListItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.itemView) View itemView;
        @BindView(R.id.commentTextView) TextView commentTextView;
    }
}
