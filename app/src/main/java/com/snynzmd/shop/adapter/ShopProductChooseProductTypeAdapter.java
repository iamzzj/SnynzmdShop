package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopProductChooseProductType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/12.
 */

public class ShopProductChooseProductTypeAdapter extends BaseAdapter<ShopProductChooseProductType, ShopProductChooseProductTypeAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;

    public ShopProductChooseProductTypeAdapter(List<ShopProductChooseProductType> list, Context context) {
        super(list, context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_product_choose_product_type_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getList().get(position).isSelect()){
            holder.view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.white));
            holder.vSelect.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.app_main));
        }else{
            holder.view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.windowBackground));
            holder.vSelect.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.windowBackground));
        }

        holder.tvName.setText(getList().get(position).getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.v_shop_product_choose_product_type_select)
        View vSelect;
        @BindView(R.id.tv_shop_product_choose_product_type_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
