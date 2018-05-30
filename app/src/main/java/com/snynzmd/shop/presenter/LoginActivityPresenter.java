package com.snynzmd.shop.presenter;

import android.content.Context;

import com.snynzmd.shop.presenter.view.LoginActivityView;
import com.snynzmd.shop.utils.SharedPreUtil;

/**
 * Created by z on 2018/3/1.
 */

public class LoginActivityPresenter {
    private Context context;
    private LoginActivityView loginActivityView;

    public LoginActivityPresenter(Context context, LoginActivityView loginActivityView) {
        this.context = context;
        this.loginActivityView = loginActivityView;
    }

    public void checkLogin(){

    }

    public void getLoginInfo(){
        loginActivityView.loginInfo(
                SharedPreUtil.getLoginUser(context),
                SharedPreUtil.getLoginPwd(context),
                SharedPreUtil.getIsSave(context),
                SharedPreUtil.getIsAuto(context)
        );
    }
}
