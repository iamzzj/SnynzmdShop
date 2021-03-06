package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopBillState;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/15.
 */

public class ShopBillStateAdapter extends BaseAdapter<ShopBillState, ShopBillStateAdapter.ViewHolder> {

    public ShopBillStateAdapter(List<ShopBillState> list, Context context) {
        super(list, context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_bill_state_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == 0){
            holder.vTop.setVisibility(View.INVISIBLE);
        }

        if(position == getItemCount()-1 ){
            holder.vBottom.setVisibility(View.INVISIBLE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_shop_bill_state_item_top)
        View vTop;
        @BindView(R.id.v_shop_bill_state_item_bottom)
        View vBottom;
        @BindView(R.id.tv_shop_bill_state_item_name)
        TextView tvName;
        @BindView(R.id.tv_shop_bill_state_item_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
