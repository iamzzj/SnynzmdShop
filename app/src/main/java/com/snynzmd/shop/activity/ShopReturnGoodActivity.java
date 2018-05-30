package com.snynzmd.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.snynzmd.shop.R;
import com.snynzmd.shop.entity.ShopBillContent;
import com.snynzmd.shop.entity.ShopBillDivider;
import com.snynzmd.shop.entity.ShopBillFoot;
import com.snynzmd.shop.entity.ShopBillHeader;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.ex.loadmore.SimpleLoadMoreViewCreator;
import net.idik.lib.slimadapter.ex.loadmore.SlimMoreLoader;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopReturnGoodActivity extends AppCompatActivity {
    @BindView(R.id.rv_shop_return_good_bill)
    RecyclerView rvShopReturnGoodBill;
    private ImmersionBar immersionBar;

    private SlimAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_return_good);
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

        GridLayoutManager productManager = new GridLayoutManager(this, 1);
        rvShopReturnGoodBill.setLayoutManager(productManager);

        billAdapter = SlimAdapter.create()
                .register(R.layout.layout_shop_bill_header_item, new SlimInjector<ShopBillHeader>() {
                    @Override
                    public void onInject(ShopBillHeader data, IViewInjector injector) {
                        injector.clicked(R.id.ll_shop_bill_header_item_look_bill, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(ShopReturnGoodActivity.this,ShopReturnGoodBillDetailActivity.class));
                            }
                        });
                    }
                })
                .register(R.layout.layout_shop_bill_content_item, new SlimInjector<ShopBillContent>() {
                    @Override
                    public void onInject(ShopBillContent data, IViewInjector injector) {

                    }
                })
                .register(R.layout.layout_shop_bill_foot_item, new SlimInjector<ShopBillFoot>() {
                    @Override
                    public void onInject(ShopBillFoot data, IViewInjector injector) {

                    }
                })
                .register(R.layout.layout_shop_bill_divider_item, new SlimInjector<ShopBillDivider>() {
                    @Override
                    public void onInject(ShopBillDivider data, IViewInjector injector) {

                    }
                })
                .enableDiff()
                .enableLoadMore(new SlimMoreLoader(this, new SimpleLoadMoreViewCreator(this).setNoMoreHint("没有更多数据了...")) {
                    @Override
                    protected void onLoadMore(Handler handler) {
                        /*SystemClock.sleep(3_000L);
                        if (random.nextInt(10) > 7) {
                            handler.error();
                        } else {
                            handler.loadCompleted(data1);
                            loadTime++;
                        }*/
                    }

                    @Override
                    protected boolean hasMore() {
                        return false;
                    }
                })
                .attachTo(rvShopReturnGoodBill);

        List<Object> data = new ArrayList<>();
        data.add(new ShopBillHeader());
        data.add(new ShopBillContent());
        data.add(new ShopBillFoot());
        billAdapter.updateData(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    @OnClick(R.id.ll_shop_return_good_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.tv_shop_return_good_apply)
    public void apply() {

    }
}
