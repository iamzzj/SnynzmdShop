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
import com.snynzmd.shop.assistantfragment.UnUsedAssistantFragment;
import com.snynzmd.shop.assistantfragment.UsedAssistantFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAssistantActivity extends AppCompatActivity {
    @BindView(R.id.btn_ac_myassistant_used)
    Button btnUsed;
    @BindView(R.id.btn_ac_myassistant_unused)
    Button btnUnused;
    @BindView(R.id.vp_ac_myassistant_viewpager)
    ViewPager vpMyassistantViewpager;

    private ImmersionBar immersionBar;

    private Fragment[] fragments = {new UsedAssistantFragment(), new UsedAssistantFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assistant);
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

        btnUsed.setSelected(true);

        vpMyassistantViewpager.setAdapter(new AssistantPagerAdapter(getSupportFragmentManager()));
        vpMyassistantViewpager.setOffscreenPageLimit(fragments.length);
        vpMyassistantViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                btnUsed.setSelected(false);
                btnUnused.setSelected(false);

                switch (position){
                    case 0:
                        btnUsed.setSelected(true);
                        break;
                    case 1:
                        btnUnused.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    @OnClick({R.id.btn_ac_myassistant_used, R.id.btn_ac_myassistant_unused})
    public void btnSelect(View view) {
        btnUsed.setSelected(false);
        btnUnused.setSelected(false);

        switch (view.getId()) {
            case R.id.btn_ac_myassistant_used:
                btnUsed.setSelected(true);
                vpMyassistantViewpager.setCurrentItem(0);
                break;
            case R.id.btn_ac_myassistant_unused:
                btnUsed.setSelected(true);
                vpMyassistantViewpager.setCurrentItem(1);
                break;
        }
    }

    private class AssistantPagerAdapter extends FragmentPagerAdapter {

        public AssistantPagerAdapter(FragmentManager fm) {
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

    @OnClick(R.id.ll_ac_myassistant_back)
    public void back() {
        finish();
    }
}
