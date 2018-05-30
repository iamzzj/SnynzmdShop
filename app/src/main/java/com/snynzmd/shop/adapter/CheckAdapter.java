package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.Check;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/8.
 */

public class CheckAdapter extends BaseAdapter<Check, CheckAdapter.ViewHolder> {
    private OnClickListener onClickListener;

    public CheckAdapter(List<Check> list, Context context) {
        super(list, context);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_check_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvCheckProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.checkProgress(position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_check_item_check_progress)
        TextView tvCheckProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnClickListener{
        void checkProgress(int position);
    }
}
