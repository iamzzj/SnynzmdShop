package com.snynzmd.shop.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.kyleduo.switchbutton.SwitchButton;
import com.snynzmd.shop.R;
import com.snynzmd.shop.adapter.VipActivityAdapter;
import com.snynzmd.shop.entity.VipActivity;
import com.snynzmd.shop.view.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by z on 2018/3/19.
 */

public class VipActivityPopupWindow extends PopupWindow {
    @BindView(R.id.rv_popup_vip_activitys)
    RecyclerView rvPopupVipActivitys;
    @BindView(R.id.sb_popup_vip_activity_join)
    SwitchButton sbJoin;
    private Context context;

    private VipActivityAdapter adapter;

    public VipActivityPopupWindow(Context context) {
        super(context);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.popup_vip_activity, null);
        ButterKnife.bind(this, view);

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.Popup_Animation_PushDownUp);
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(context, R.color.nullBackground));
        this.setBackgroundDrawable(dw);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setOnDismissListener(new PoponDismissListener());

        // 活动
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPopupVipActivitys.setLayoutManager(linearLayoutManager);
        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        rvPopupVipActivitys.addItemDecoration(new VerticalDividerItemDecoration.Builder(context).paint(paint).build());

        adapter = new VipActivityAdapter(new ArrayList<VipActivity>(), context);
        rvPopupVipActivitys.setAdapter(adapter);

        List<VipActivity> list = new ArrayList<>();
        list.add(new VipActivity());
        list.add(new VipActivity());
        list.add(new VipActivity());
        list.add(new VipActivity());
        list.add(new VipActivity());

        adapter.clearAddAll(list);
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
