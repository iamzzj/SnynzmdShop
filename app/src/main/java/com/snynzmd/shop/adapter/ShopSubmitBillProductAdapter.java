package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopCartProduct;

import java.util.List;

/**
 * Created by z on 2018/3/14.
 */

public class ShopSubmitBillProductAdapter extends BaseAdapter<ShopCartProduct,ShopSubmitBillProductAdapter.ViewHolder> {


    public ShopSubmitBillProductAdapter(List<ShopCartProduct> list, Context context) {
        super(list, context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_submit_bill_product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
