package com.snynzmd.shop.menufragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.snynzmd.shop.R;
import com.snynzmd.shop.activity.ChargeManageActivity;
import com.snynzmd.shop.activity.ShopBillManageActivity;
import com.snynzmd.shop.activity.ShopReturnGoodActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by z on 2018/2/28.
 */

public class ShopFragment extends Fragment {
    @BindView(R.id.v_shop_bar)
    View vShopBar;
    @BindView(R.id.b_shop_tops)
    Banner bShopTops;
    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        unBinder = ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vShopBar.setVisibility(View.VISIBLE);
        }

        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.shop_top1);
        images.add(R.mipmap.shop_top2);
        images.add(R.mipmap.shop_top3);
        //设置banner样式
        bShopTops.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bShopTops.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View view) {
                ImageView imageView = (ImageView) view;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(path).into(imageView);
            }

            @Override
            public View createImageView(Context context) {
                return null;
            }
        });
        //设置图片集合
        bShopTops.setImages(images);
        //设置banner动画效果
        bShopTops.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        bShopTops.isAutoPlay(true);
        //设置轮播时间
        bShopTops.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        bShopTops.start();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //开始轮播
        bShopTops.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        bShopTops.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @OnClick(R.id.ll_shop_chargemanage)
    public void chargemanage() {
        startActivity(new Intent(getActivity(), ChargeManageActivity.class));
    }

    @OnClick(R.id.ll_shop_billmanage)
    public void billmanage() {
        startActivity(new Intent(getActivity(), ShopBillManageActivity.class));
    }

    @OnClick(R.id.h2w_vip_return_good)
    public void returnGood() {
        startActivity(new Intent(getActivity(), ShopReturnGoodActivity.class));
    }
}
