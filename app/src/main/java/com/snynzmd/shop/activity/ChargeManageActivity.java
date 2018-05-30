package com.snynzmd.shop.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.snynzmd.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChargeManageActivity extends AppCompatActivity {
    @BindView(R.id.v_charge_manage_bar)
    View vChargeManageBar;
    @BindView(R.id.ctl_charge_manage)
    CollapsingToolbarLayout ctlChargeManage;

    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_manage);
        ButterKnife.bind(this);

        immersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.white)  //修改flyme OS状态栏字体颜色
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        immersionBar.init();  //必须调用方可沉浸式

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vChargeManageBar.setVisibility(View.VISIBLE);
        }

        //下拉一瞬间颜色
        ctlChargeManage.setContentScrimResource(R.drawable.app_main_top);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null)
            immersionBar.destroy();
    }

    @OnClick(R.id.ll_charge_manage_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.ll_charge_manage_charge)
    public void charge() {
        startActivity(new Intent(ChargeManageActivity.this, ChargeActivity.class));
    }

    @OnClick(R.id.ll_charge_manage_charge_history)
    public void chargeHistory() {
        startActivity(new Intent(ChargeManageActivity.this, ChargeHistoryActivity.class));
    }

    @OnClick(R.id.ll_charge_manage_consumption_history)
    public void consumptionHistory() {
        startActivity(new Intent(ChargeManageActivity.this, ConsumptionHistoryActivity.class));
    }
}
