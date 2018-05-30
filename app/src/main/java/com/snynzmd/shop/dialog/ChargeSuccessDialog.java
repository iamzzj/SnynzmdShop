package com.snynzmd.shop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.snynzmd.shop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by z on 2018/3/16.
 */

public class ChargeSuccessDialog extends Dialog {


    public ChargeSuccessDialog(@NonNull Context context) {
        super(context);
    }

    public ChargeSuccessDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected ChargeSuccessDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_charge_success, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        WindowManager windowManager = getWindow().getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = displayMetrics.widthPixels * 5 / 6;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(false);// 点击Dialog外部消失
    }

    @OnClick(R.id.iv_dialog_charge_success_close)
    public void close() {
        dismiss();
    }
}
