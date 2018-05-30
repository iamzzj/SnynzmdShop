package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopCartProduct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/13.
 */

public class ShopCartProductAdapter extends RecyclerSwipeAdapter<ShopCartProductAdapter.ViewHolder> {

    private Context context;
    private List<ShopCartProduct> list = new ArrayList<>();

    public ShopCartProductAdapter(Context context, List<ShopCartProduct> list) {
        this.context = context;
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_cart_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.slSwipe.setShowMode(SwipeLayout.ShowMode.LayDown);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.sl_shop_cart_product_item_swipe;
    }

    public List<ShopCartProduct> getList() {
        return this.list;
    }

    public Context getContext() {
        return this.context;
    }

    public void addAll(List<ShopCartProduct> listCache){
        if(listCache != null) {
            list.clear();
            list.addAll(listCache);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sl_shop_cart_product_item_swipe)
        SwipeLayout slSwipe;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
