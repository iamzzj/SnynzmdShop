package com.snynzmd.shop.activity;

import android.content.Intent;
import android.os.Build;
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
import com.snynzmd.shop.R;
import com.snynzmd.shop.shopnormalbillfragment.ShopDfhFragment;
import com.snynzmd.shop.shopnormalbillfragment.ShopDqsFragment;
import com.snynzmd.shop.shopnormalbillfragment.ShopYqsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopNormalBillActivity extends AppCompatActivity {
    @BindView(R.id.v_shop_normal_bill_bar)
    View vShopNormalBillBar;
    @BindView(R.id.btn_shop_normal_bill_dfh)
    Button btnDfh;
    @BindView(R.id.btn_shop_normal_bill_dqs)
    Button btnDqs;
    @BindView(R.id.btn_shop_normal_bill_yqs)
    Button btnYqs;
    @BindView(R.id.vp_shop_normal_bill_bills)
    ViewPager vpBills;

    private ImmersionBar immersionBar;

    private Fragment[] fragments = {new ShopDfhFragment(),new ShopDqsFragment(),new ShopYqsFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_normal_bill);
        ButterKnife.bind(this);

        immersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.white)  //修改flyme OS状态栏字体颜色
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        immersionBar.init();  //必须调用方可沉浸式

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vShopNormalBillBar.setVisibility(View.VISIBLE);
        }

        vpBills.setAdapter(new BillPagerAdapter(getSupportFragmentManager()));
        vpBills.setOffscreenPageLimit(fragments.length);
        vpBills.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                btnDfh.setSelected(false);
                btnDqs.setSelected(false);
                btnYqs.setSelected(false);

                switch (position){
                    case 0:
                        btnDfh.setSelected(true);
                        break;
                    case 1:
                        btnDqs.setSelected(true);
                        break;
                    case 2:
                        btnYqs.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnDfh.setSelected(true);
    }

    @OnClick(R.id.ll_shop_normal_bill_back)
    public void back() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null)
            immersionBar.destroy();
    }

    @OnClick(R.id.tv_shop_normal_bill_fast_bill)
    public void fastbill() {
        startActivity(new Intent(this, ShopProductChooseActivity.class));
    }

    @OnClick({R.id.btn_shop_normal_bill_dfh, R.id.btn_shop_normal_bill_dqs, R.id.btn_shop_normal_bill_yqs})
    public void buttons(Button button) {
        btnDfh.setSelected(false);
        btnDqs.setSelected(false);
        btnYqs.setSelected(false);

        button.setSelected(true);

        switch (button.getId()) {
            case R.id.btn_shop_normal_bill_dfh:
                vpBills.setCurrentItem(0);
                break;
            case R.id.btn_shop_normal_bill_dqs:
                vpBills.setCurrentItem(1);
                break;
            case R.id.btn_shop_normal_bill_yqs:
                vpBills.setCurrentItem(2);
                break;
        }
    }

    private class BillPagerAdapter extends FragmentPagerAdapter{

        public BillPagerAdapter(FragmentManager fm) {
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
