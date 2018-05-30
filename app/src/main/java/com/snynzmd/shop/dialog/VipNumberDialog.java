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
import android.widget.EditText;

import com.snynzmd.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by z on 2018/3/19.
 */

public class VipNumberDialog extends Dialog {
    @BindView(R.id.et_dialog_vip_number_phone)
    EditText etDialogVipNumberPhone;
    private OnClickListener onClickListener;

    public VipNumberDialog(@NonNull Context context) {
        super(context);
    }

    public VipNumberDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected VipNumberDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_vip_number, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        WindowManager windowManager = getWindow().getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = displayMetrics.widthPixels * 5 / 6;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);// 点击Dialog外部消失
    }

    @OnClick(R.id.tv_dialog_vip_number_sure)
    public void sure() {
        dismiss();
        if(onClickListener!=null){
            onClickListener.sure(etDialogVipNumberPhone.getText().toString());
        }
    }

    public interface OnClickListener {
        void sure(String phone);
    }
}
