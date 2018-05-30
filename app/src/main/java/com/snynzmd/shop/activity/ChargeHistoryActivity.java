package com.snynzmd.shop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.snynzmd.shop.R;
import com.snynzmd.shop.chargehistoryfragment.ChargeSuccessFragment;
import com.snynzmd.shop.chargehistoryfragment.CheckFailFragment;
import com.snynzmd.shop.chargehistoryfragment.CheckFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChargeHistoryActivity extends AppCompatActivity {
    @BindView(R.id.btn_charge_history_charge_success)
    Button btnChargeSuccess;
    @BindView(R.id.btn_charge_history_check)
    Button btnCheck;
    @BindView(R.id.btn_charge_history_check_fail)
    Button btnCheckFail;
    @BindView(R.id.vp_charge_history)
    ViewPager vpChargeHistory;

    private ImmersionBar immersionBar;

    private Fragment[] fragments = {new ChargeSuccessFragment(), new CheckFragment(), new CheckFailFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_history);
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

        vpChargeHistory.setAdapter(new ChargeHistoryPagerAdapter(getSupportFragmentManager()));
        vpChargeHistory.setOffscreenPageLimit(fragments.length);
        vpChargeHistory.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                btnChargeSuccess.setSelected(false);
                btnCheck.setSelected(false);
                btnCheckFail.setSelected(false);
                switch (position){
                    case 0:
                        btnChargeSuccess.setSelected(true);
                        break;
                    case 1:
                        btnCheck.setSelected(true);
                        break;
                    case 2:
                        btnCheckFail.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnChargeSuccess.setSelected(true);

    }

    @OnClick(R.id.ll_ac_charge_history_back)
    public void back() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    @OnClick({R.id.btn_charge_history_charge_success, R.id.btn_charge_history_check, R.id.btn_charge_history_check_fail})
    public void btns(View view) {
        btnChargeSuccess.setSelected(false);
        btnCheck.setSelected(false);
        btnCheckFail.setSelected(false);
        switch (view.getId()) {
            case R.id.btn_charge_history_charge_success:
                vpChargeHistory.setCurrentItem(0);
                btnChargeSuccess.setSelected(true);
                break;
            case R.id.btn_charge_history_check:
                vpChargeHistory.setCurrentItem(1);
                btnCheck.setSelected(true);
                break;
            case R.id.btn_charge_history_check_fail:
                vpChargeHistory.setCurrentItem(2);
                btnCheckFail.setSelected(true);
                break;
        }
    }

    private class ChargeHistoryPagerAdapter extends FragmentPagerAdapter {

        public ChargeHistoryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
