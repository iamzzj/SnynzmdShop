package com.snynzmd.shop.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.snynzmd.shop.R;
import com.snynzmd.shop.adapter.ShopSubmitBillProductAdapter;
import com.snynzmd.shop.entity.ShopCartProduct;
import com.snynzmd.shop.view.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopSubmitBillActivity extends AppCompatActivity {
    @BindView(R.id.rv_shop_submit_bill_products)
    RecyclerView rvProducts;
    private ImmersionBar immersionBar;

    private ShopSubmitBillProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_submit_bill);
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

        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setColor(ContextCompat.getColor(this, R.color.white));
        rvProducts.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());
        rvProducts.setNestedScrollingEnabled(false);

        adapter = new ShopSubmitBillProductAdapter(new ArrayList<ShopCartProduct>(), this);
        rvProducts.setAdapter(adapter);

        List<ShopCartProduct> list = new ArrayList<>();
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());
        list.add(new ShopCartProduct());

        adapter.clearAddAll(list);
    }

    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    @OnClick(R.id.ll_shop_submit_bill_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.ll_shop_submit_bill_adress_choose)
    public void adressChoose() {
        startActivity(new Intent(this, ShopAdressActivity.class));
    }

    @OnClick(R.id.btn_shop_submit_bill_submit)
    public void submit() {
        startActivity(new Intent(this,ShopPayActivity.class));
    }
}
