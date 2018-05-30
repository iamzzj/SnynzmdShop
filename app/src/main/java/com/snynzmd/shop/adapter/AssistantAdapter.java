package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiang.android.lib.adapter.BaseAdapter;
import com.jiang.android.lib.adapter.expand.StickyRecyclerHeadersAdapter;
import com.orhanobut.logger.Logger;
import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.Assistant;
import com.snynzmd.shop.widget.IndexAdapter;
import com.snynzmd.shop.widget.Indexable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/5.
 */

public class AssistantAdapter extends BaseAdapter<Assistant, AssistantAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<AssistantAdapter.HeaderViewHolder> , IndexAdapter{
    private Context context;

    public AssistantAdapter(List<Assistant> list, Context context) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.addAll(list);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_assistant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAssistantItemName.setText(getItem(position).getName());
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getSortLetters().charAt(0);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_assistant_header_item, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder headerViewHolder, int i) {
        String showValue = String.valueOf(getItem(i).getSortLetters().charAt(0));
        headerViewHolder.tvAssistantHeaderItemName.setText(showValue);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_assistant_header_item_name)
        TextView tvAssistantHeaderItemName;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_assistant_item_name)
        TextView tvAssistantItemName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = getItem(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            //Logger.i(firstChar+" -- "+section);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;

    }
}
