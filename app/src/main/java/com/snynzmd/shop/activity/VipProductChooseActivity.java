package com.snynzmd.shop.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.snynzmd.shop.R;
import com.snynzmd.shop.adapter.ShopProductChooseProductTypeAdapter;
import com.snynzmd.shop.dialog.VipNumberDialog;
import com.snynzmd.shop.entity.ShopProductChooseProduct;
import com.snynzmd.shop.entity.ShopProductChooseProductHeader;
import com.snynzmd.shop.entity.ShopProductChooseProductType;
import com.snynzmd.shop.popupwindow.ShopCartPopupWindow;
import com.snynzmd.shop.popupwindow.VipActivityPopupWindow;
import com.snynzmd.shop.view.HorizontalDividerItemDecoration;

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

public class VipProductChooseActivity extends AppCompatActivity  implements ShopProductChooseProductTypeAdapter.OnItemClickListener{

    @BindView(R.id.rv_vip_product_choose_product_type)
    RecyclerView rvProductType;
    @BindView(R.id.rv_vip_product_choose_product)
    RecyclerView rvProduct;
    private ImmersionBar immersionBar;

    private ShopProductChooseProductTypeAdapter productTypeAdapter;
    private SlimAdapter productAdapter;

    private ShopCartPopupWindow cartPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_product_choose);
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

        cartPopupWindow = new ShopCartPopupWindow(this);

        // 产品类型
        rvProductType.setLayoutManager(new LinearLayoutManager(this));
        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setColor(ContextCompat.getColor(this, R.color.windowBackground));
        rvProductType.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());

        productTypeAdapter = new ShopProductChooseProductTypeAdapter(new ArrayList<ShopProductChooseProductType>(), this);
        productTypeAdapter.setOnItemClickListener(this);
        rvProductType.setAdapter(productTypeAdapter);

        // 产品
        GridLayoutManager productManager = new GridLayoutManager(this, 2);
        productManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return productAdapter.getItem(position) instanceof ShopProductChooseProduct ? 1 : 2;
            }
        });
        rvProduct.setLayoutManager(productManager);

        productAdapter = SlimAdapter.create()
                .register(R.layout.layout_shop_product_choose_product_header_item, new SlimInjector<ShopProductChooseProductHeader>() {
                    @Override
                    public void onInject(ShopProductChooseProductHeader data, IViewInjector injector) {
                        injector.text(R.id.tv_shop_product_choose_product_header_item_name, data.getName());
                    }
                })
                .register(R.layout.layout_shop_product_choose_product_item, new SlimInjector<ShopProductChooseProduct>() {
                    @Override
                    public void onInject(final ShopProductChooseProduct data, IViewInjector injector) {
                        injector.text(R.id.tv_shop_product_choose_product_item_name, data.getName());
                        injector.text(R.id.tv_shop_product_choose_product_item_price, data.getPrice());

                        injector.clicked(R.id.ll_shop_product_choose_product_item_item, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toasty.info(ShopProductChooseActivity.this,data.getName(), Toast.LENGTH_LONG).show();
                                cartPopupWindow.showAtLocation(v, Gravity.BOTTOM, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            }
                        });
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
                .attachTo(rvProduct);

        // 产品类型
        List<ShopProductChooseProductType> productTypes = new ArrayList<>();
        ShopProductChooseProductType type = new ShopProductChooseProductType("奶粉");
        type.setSelect(true);
        productTypes.add(type);
        productTypes.add(new ShopProductChooseProductType("辅食"));
        productTypes.add(new ShopProductChooseProductType("辅食"));
        productTypes.add(new ShopProductChooseProductType("辅食辅食辅食辅食辅食辅食辅食"));
        productTypes.add(new ShopProductChooseProductType("辅食"));
        productTypes.add(new ShopProductChooseProductType("奶粉1"));
        productTypes.add(new ShopProductChooseProductType("奶粉2"));
        productTypes.add(new ShopProductChooseProductType("辅食1"));
        productTypes.add(new ShopProductChooseProductType("辅食3"));
        productTypes.add(new ShopProductChooseProductType("辅食4"));
        productTypes.add(new ShopProductChooseProductType("辅食5"));
        productTypes.add(new ShopProductChooseProductType("辅食6"));
        productTypes.add(new ShopProductChooseProductType("辅食7"));
        productTypes.add(new ShopProductChooseProductType("辅食8"));
        productTypeAdapter.addAll(productTypes);

        // 产品
        List<Object> products = new ArrayList<>();
        products.add(new ShopProductChooseProductHeader("1段"));
        products.add(new ShopProductChooseProduct("产品1", "200"));
        products.add(new ShopProductChooseProduct("产品2", "200"));
        products.add(new ShopProductChooseProduct("产品3", "200"));
        products.add(new ShopProductChooseProductHeader("2段"));
        products.add(new ShopProductChooseProduct("产品2", "200"));
        products.add(new ShopProductChooseProduct("产品3", "200"));
        products.add(new ShopProductChooseProductHeader("3段"));
        products.add(new ShopProductChooseProduct("产品1", "200"));
        products.add(new ShopProductChooseProductHeader("4段"));
        products.add(new ShopProductChooseProduct("产品1", "400"));
        products.add(new ShopProductChooseProduct("产品222222222222222222222222222222222222222222222222222222222222222222222222", "300"));
        products.add(new ShopProductChooseProduct("产品3", "200"));
        products.add(new ShopProductChooseProduct("产品4", "100"));

        productAdapter.updateData(products);

        VipNumberDialog vipNumberDialog = new VipNumberDialog(this,R.style.dialog_nobackground);
        vipNumberDialog.setOnClickListener(new VipNumberDialog.OnClickListener() {
            @Override
            public void sure(String phone) {
                VipActivityPopupWindow vipActivityPopupWindow = new VipActivityPopupWindow(VipProductChooseActivity.this);
                vipActivityPopupWindow.showAtLocation(rvProduct, Gravity.BOTTOM, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            }
        });
        vipNumberDialog.show();
    }

    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    @OnClick(R.id.ll_vip_product_choose_back)
    public void back() {
        finish();
    }

    @Override
    public void OnItemClick(int position) {
        if (productTypeAdapter.getList() != null) {
            for (ShopProductChooseProductType type : productTypeAdapter.getList()) {
                type.setSelect(false);
            }
            productTypeAdapter.getList().get(position).setSelect(true);
            productTypeAdapter.notifyDataSetChanged();
        }
    }
}
