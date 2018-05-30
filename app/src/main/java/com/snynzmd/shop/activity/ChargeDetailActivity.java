package com.snynzmd.shop.activity;

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

public class ChargeDetailActivity extends AppCompatActivity {
    @BindView(R.id.v_charge_detail_bar)
    View vChargeDetailBar;
    @BindView(R.id.ctl_charge_detail)
    CollapsingToolbarLayout ctlChargeDetail;

    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_detail);
        ButterKnife.bind(this);

        immersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.white)  //修改flyme OS状态栏字体颜色
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        immersionBar.init();  //必须调用方可沉浸式

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vChargeDetailBar.setVisibility(View.VISIBLE);
        }

        //下拉一瞬间颜色
        ctlChargeDetail.setContentScrimResource(R.drawable.app_main_top);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null)
            immersionBar.destroy();
    }

    @OnClick(R.id.ll_charge_detail_back)
    public void back() {
        finish();
    }
}
