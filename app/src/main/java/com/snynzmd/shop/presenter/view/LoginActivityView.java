package com.snynzmd.shop.presenter.view;

/**
 * Created by z on 2018/3/1.
 */

public interface LoginActivityView {
    void dialogShow();

    void dialogDismiss();

    String getUser();

    String getPwd();

    boolean isSave();

    boolean isAuto();

    void loginInfo(String user, String pwd, boolean isSave, boolean isAuto);

    void err(String err);

    void loginSuccess();
}
