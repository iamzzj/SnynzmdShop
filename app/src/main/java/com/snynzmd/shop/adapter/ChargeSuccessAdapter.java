package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ChargeSuccess;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/8.
 */

public class ChargeSuccessAdapter extends BaseAdapter<ChargeSuccess,ChargeSuccessAdapter.ViewHolder>{
    private OnItemClickListener onItemClickListener;

    public ChargeSuccessAdapter(List<ChargeSuccess> list, Context context) {
        super(list, context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_charge_success_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener !=null ){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
