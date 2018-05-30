package com.snynzmd.shop.menufragment;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;
import com.snynzmd.shop.activity.TraceActivity;
import com.snynzmd.shop.activity.VipExchangeActivity;
import com.snynzmd.shop.activity.VipProductChooseActivity;
import com.snynzmd.shop.activity.VipRegistActivity;
import com.snynzmd.shop.utils.PermissionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by z on 2018/2/28.
 */

public class VipFragment extends Fragment {
    private static final int PERMISION_CAMERA = 10001;

    @BindView(R.id.v_vip_bar)
    View vVipBar;
    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vip, container, false);
        unBinder = ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vVipBar.setVisibility(View.VISIBLE);
        }

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    /**
     * 追溯
     */
    @OnClick(R.id.h2w_vip_trace)
    public void trace() {
        //大于6.0动态权限 检测权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !PermissionUtils.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)) {
            PermissionUtils.requestPermission(getActivity(), PERMISION_CAMERA, Manifest.permission.CAMERA);
        } else {
            startActivity(new Intent(getActivity(), TraceActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISION_CAMERA) {
            if (PermissionUtils.getPermissionResult(grantResults)) {
                startActivity(new Intent(getActivity(), TraceActivity.class));
            }
        }
    }

    /**
     * 协助注册
     */
    @OnClick(R.id.h2w_vip_regist)
    public void regist() {
        startActivity(new Intent(getActivity(), VipRegistActivity.class));
    }

    /**
     * 协助兑换
     */
    @OnClick(R.id.h2w_vip_exchange)
    public void exchange() {
        startActivity(new Intent(getActivity(), VipExchangeActivity.class));
    }

    @OnClick(R.id.h2w_vip_bill)
    public void bill() {
        startActivity(new Intent(getActivity(), VipProductChooseActivity.class));
    }
}
