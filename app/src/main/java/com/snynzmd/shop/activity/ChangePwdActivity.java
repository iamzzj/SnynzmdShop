package com.snynzmd.shop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.snynzmd.shop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwdActivity extends AppCompatActivity {
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);

        immersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.black)  //修改flyme OS状态栏字体颜色
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调
                    @Override
                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
                        //isPopup为true，软键盘弹出，为false，软键盘关闭
                    }
                });
        immersionBar.init();  //必须调用方可沉浸式

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    @OnClick(R.id.ll_ac_changepwd_back)
    public void back() {
        finish();
    }
}
