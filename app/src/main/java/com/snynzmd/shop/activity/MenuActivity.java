package com.snynzmd.shop.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.snynzmd.shop.R;
import com.snynzmd.shop.menufragment.MyFragment;
import com.snynzmd.shop.menufragment.ShopFragment;
import com.snynzmd.shop.menufragment.TableFragment;
import com.snynzmd.shop.menufragment.VipFragment;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.vp_menu_viewpager)
    ViewPager vpMenuViewpager;
    @BindView(R.id.bnv_menu_menu)
    BottomNavigationView bnvMenuMenu;

    private ImmersionBar immersionBar;

    private final Fragment[] fragments = {new ShopFragment(),new VipFragment(),new TableFragment(),new MyFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        immersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.black)  //修改flyme OS状态栏字体颜色
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        immersionBar.init();  //必须调用方可沉浸式

        disableShiftMode(bnvMenuMenu);

        vpMenuViewpager.setAdapter(new MenuFragmentAdapter(getSupportFragmentManager()));
        vpMenuViewpager.setOffscreenPageLimit(fragments.length);
        vpMenuViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bnvMenuMenu.getMenu().size();
                for (int i = 0; i < bnvMenuMenu.getMenu().size(); i++) {
                    bnvMenuMenu.getMenu().getItem(i).setChecked(false);
                }
                bnvMenuMenu.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bnvMenuMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_navigation_shop:
                        vpMenuViewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_navigation_vip:
                        vpMenuViewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_navigation_table:
                        vpMenuViewpager.setCurrentItem(2);
                        break;
                    case R.id.menu_navigation_my:
                        vpMenuViewpager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 禁用超过3个以上动画
     *
     * @param view
     */
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private class MenuFragmentAdapter extends FragmentPagerAdapter{

        public MenuFragmentAdapter(FragmentManager fm) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null)
            immersionBar.destroy();
    }
}
