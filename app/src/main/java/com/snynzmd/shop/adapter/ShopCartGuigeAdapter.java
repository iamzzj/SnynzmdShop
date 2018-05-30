package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopCartGuige;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/13.
 */

public class ShopCartGuigeAdapter extends BaseAdapter<ShopCartGuige, ShopCartGuigeAdapter.ViewHolder> {

    public ShopCartGuigeAdapter(List<ShopCartGuige> list, Context context) {
        super(list, context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pop_shop_cart_guige_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pop_shop_cart_guige_item_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
