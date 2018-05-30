package com.snynzmd.shop.menufragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.snynzmd.shop.R;
import com.snynzmd.shop.activity.InventoryActivity;
import com.snynzmd.shop.activity.WarningActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by z on 2018/2/28.
 */

public class TableFragment extends Fragment {
    @BindView(R.id.v_table_bar)
    View vTableBar;
    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        unBinder = ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vTableBar.setVisibility(View.VISIBLE);
        }

        Logger.i("table-oncreate");

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @OnClick(R.id.ll_table_inventory)
    public void inventory() {
        startActivity(new Intent(getActivity(), InventoryActivity.class));
    }

    @OnClick(R.id.ll_table_warning)
    public void warning() {
        startActivity(new Intent(getActivity(), WarningActivity.class));
    }
}
