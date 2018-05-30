package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopBillManage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/12.
 */

public class ShopBillManageAdapter extends BaseAdapter<ShopBillManage, ShopBillManageAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;

    public ShopBillManageAdapter(List<ShopBillManage> list, Context context) {
        super(list, context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_bill_manage_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        switch (getList().get(position).getBillType()){
            case ShopNormalBill:
                holder.ivImage.setImageResource(R.mipmap.shop_bill_manage_zc);
                holder.tvName.setText("正常订单");
                break;
            case ShopPresellBill:
                holder.ivImage.setImageResource(R.mipmap.shop_bill_manage_ys);
                holder.tvName.setText("预售订单");
                break;
            case ShopNakedPriceBill:
                holder.ivImage.setImageResource(R.mipmap.shop_bill_manage_lj);
                holder.tvName.setText("裸价订单");
                break;
            case ShopActivityBill:
                holder.ivImage.setImageResource(R.mipmap.shop_bill_manage_hd);
                holder.tvName.setText("活动订单");
                break;
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.iv_shop_bill_manage_item_image)
        ImageView ivImage;
        @BindView(R.id.tv_shop_bill_manage_item_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
