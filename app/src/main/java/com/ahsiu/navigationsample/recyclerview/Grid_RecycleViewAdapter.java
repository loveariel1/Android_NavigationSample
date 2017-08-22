package com.ahsiu.navigationsample.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahsiu.navigationsample.R;

import java.util.List;

public class Grid_RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List data;

    private Grid_RecycleViewAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(Grid_RecycleViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Grid_RecycleViewAdapter(Context context, List data) {
        this.context = context;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_base_grid, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Grid_RecycleViewAdapter.ItemViewHolder) {
            if (onItemClickListener != null) {
                ((ItemViewHolder) holder).textView.setText(position + "");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
