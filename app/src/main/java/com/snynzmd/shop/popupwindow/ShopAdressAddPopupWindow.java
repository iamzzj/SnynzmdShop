package com.snynzmd.shop.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.snynzmd.shop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by z on 2018/3/16.
 */

public class ShopAdressAddPopupWindow extends PopupWindow {
    private Context context;

    public ShopAdressAddPopupWindow(Context context) {
        super(context);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.popup_shop_adress_add, null);
        ButterKnife.bind(this, view);

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        //this.setAnimationStyle(R.style.Popup_Animation_PushDownUp);
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(context, R.color.nullBackground));
        this.setBackgroundDrawable(dw);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setOnDismissListener(new PoponDismissListener());
    }

    @OnClick(R.id.iv_shop_adress_add_back)
    public void back() {
        dismiss();
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class PoponDismissListener implements OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        backgroundAlpha(0.7f);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }
}
