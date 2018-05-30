package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.VipActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/19.
 */

public class VipActivityAdapter extends BaseAdapter<VipActivity,VipActivityAdapter.ViewHolder>{

    public VipActivityAdapter(List<VipActivity> list, Context context) {
        super(list, context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vip_activity_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
