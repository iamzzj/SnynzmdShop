package com.snynzmd.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z on 2018/3/8.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{
    private List<T> list = new ArrayList<>();
    private Context context;

    public BaseAdapter(List<T> list, Context context) {
        if(list == null){
            list = new ArrayList<>();
        }
        this.list.clear();
        this.list.addAll(list);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<T> getList() {
        return this.list;
    }

    public Context getContext() {
        return this.context;
    }

    public void addAll(List<T> listCache){
        if(listCache != null && list!=null) {
            list.addAll(listCache);
            notifyDataSetChanged();
        }
    }

    public void clearAddAll(List<T> listCache){
        if(listCache != null && list!=null) {
            list.clear();
            list.addAll(listCache);
            notifyDataSetChanged();
        }
    }
}
