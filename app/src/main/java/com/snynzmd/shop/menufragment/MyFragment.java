package com.snynzmd.shop.menufragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;
import com.snynzmd.shop.activity.ChangePwdActivity;
import com.snynzmd.shop.activity.MyAssistantActivity;
import com.snynzmd.shop.activity.UserInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by z on 2018/2/28.
 */

public class MyFragment extends Fragment {
    @BindView(R.id.ctl_my)
    CollapsingToolbarLayout ctlMy;
    @BindView(R.id.v_my_bar)
    View vMyBar;
    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unBinder = ButterKnife.bind(this, view);

        //下拉一瞬间颜色
        ctlMy.setContentScrimResource(R.drawable.app_main_top);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vMyBar.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.ll_my_userinfo)
    public void userinfo() {
        startActivity(new Intent(getActivity(), UserInfoActivity.class));
    }

    @OnClick(R.id.ll_my_changepwd)
    public void changepwd() {
        startActivity(new Intent(getActivity(), ChangePwdActivity.class));
    }

    @OnClick(R.id.ll_my_myassistant)
    public void myassistant() {
        startActivity(new Intent(getActivity(), MyAssistantActivity.class));
    }
}
