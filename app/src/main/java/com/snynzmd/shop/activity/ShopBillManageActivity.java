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
import com.snynzmd.shop.adapter.ShopBillManageAdapter;
import com.snynzmd.shop.entity.ShopBillManage;
import com.snynzmd.shop.view.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopBillManageActivity extends AppCompatActivity implements ShopBillManageAdapter.OnItemClickListener{
    @BindView(R.id.rv_shop_bill_manage_billmanage)
    RecyclerView rvBillmanage;

    private ImmersionBar immersionBar;

    private ShopBillManageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_bill_manage);
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

        rvBillmanage.setLayoutManager(new LinearLayoutManager(this));
        Paint paint = new Paint();
        paint.setStrokeWidth(40);
        paint.setColor(ContextCompat.getColor(this, R.color.windowBackground));
        rvBillmanage.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());

        adapter= new ShopBillManageAdapter(new ArrayList<ShopBillManage>(),this);
        adapter.setOnItemClickListener(this);
        rvBillmanage.setAdapter(adapter);

        List<ShopBillManage> list = new ArrayList<>();
        list.add(new ShopBillManage(ShopBillManage.BillType.ShopNormalBill));
        list.add(new ShopBillManage(ShopBillManage.BillType.ShopPresellBill));
        list.add(new ShopBillManage(ShopBillManage.BillType.ShopNakedPriceBill));
        list.add(new ShopBillManage(ShopBillManage.BillType.ShopActivityBill));
        adapter.addAll(list);
    }

    @Override
    public void onItemClick(int position) {
        if(adapter.getList()!=null){
            switch (adapter.getList().get(position).getBillType()){
                case ShopNormalBill:
                    startActivity(new Intent(this,ShopNormalBillActivity.class));
                    break;
                case ShopPresellBill:

                    break;
                case ShopNakedPriceBill:

                    break;
                case ShopActivityBill:

                    break;
            }
        }
    }

    @OnClick(R.id.ll_shop_bill_manage_back)
    public void back() {
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

}
