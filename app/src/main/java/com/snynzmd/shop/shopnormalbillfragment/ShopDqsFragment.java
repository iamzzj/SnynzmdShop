package com.snynzmd.shop.shopnormalbillfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;
import com.snynzmd.shop.activity.ShopBillDetailActivity;
import com.snynzmd.shop.activity.ShopBillStateActivity;
import com.snynzmd.shop.entity.ShopBillAction;
import com.snynzmd.shop.entity.ShopBillContent;
import com.snynzmd.shop.entity.ShopBillDivider;
import com.snynzmd.shop.entity.ShopBillFoot;
import com.snynzmd.shop.entity.ShopBillHeader;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by z on 2018/3/13.
 */

public class ShopDqsFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.rv_shop_dqs_bills)
    RecyclerView rvBills;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    private SlimAdapter billAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_dqs, container, false);

        unbinder = ButterKnife.bind(this, view);

        GridLayoutManager productManager = new GridLayoutManager(getActivity(), 1);
        rvBills.setLayoutManager(productManager);

        billAdapter = SlimAdapter.create()
                .register(R.layout.layout_shop_bill_header_item , new SlimInjector<ShopBillHeader>(){
                    @Override
                    public void onInject(ShopBillHeader data, IViewInjector injector) {
                        injector.clicked(R.id.ll_shop_bill_header_item_look_bill, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), ShopBillDetailActivity.class));
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
                .register(R.layout.layout_shop_bill_action_item, new SlimInjector<ShopBillAction>() {

                    @Override
                    public void onInject(ShopBillAction data, IViewInjector injector) {
                        injector.clicked(R.id.tv_shop_bill_action_item_look, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), ShopBillStateActivity.class));
                            }
                        });
                    }
                })
                .register(R.layout.layout_shop_bill_divider_item, new SlimInjector<ShopBillDivider>() {
                    @Override
                    public void onInject(ShopBillDivider data, IViewInjector injector) {

                    }
                })
                .enableDiff()
                .attachTo(rvBills);



        isViewCreated = true;
        lazyLoad();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

            List<Object> data = new ArrayList<>();
            data.add(new ShopBillHeader());
            data.add(new ShopBillContent());
            data.add(new ShopBillContent());
            data.add(new ShopBillContent());
            data.add(new ShopBillFoot());
            data.add(new ShopBillAction());
            data.add(new ShopBillDivider());
            data.add(new ShopBillHeader());
            data.add(new ShopBillContent());
            data.add(new ShopBillFoot());
            data.add(new ShopBillAction());
            data.add(new ShopBillDivider());
            data.add(new ShopBillHeader());
            data.add(new ShopBillContent());
            data.add(new ShopBillContent());
            data.add(new ShopBillContent());
            data.add(new ShopBillContent());
            data.add(new ShopBillFoot());
            data.add(new ShopBillAction());
            billAdapter.updateData(data);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
