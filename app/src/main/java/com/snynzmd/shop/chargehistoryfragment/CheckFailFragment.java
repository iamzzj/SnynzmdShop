package com.snynzmd.shop.chargehistoryfragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.snynzmd.shop.R;
import com.snynzmd.shop.adapter.CheckFailAdapter;
import com.snynzmd.shop.entity.CheckFail;
import com.snynzmd.shop.view.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by z on 2018/3/8.
 */

public class CheckFailFragment extends Fragment {
    @BindView(R.id.rv_check_fail)
    RecyclerView rv;
    @BindView(R.id.srl_check_fail_swipe)
    SwipeRefreshLayout srlSwipe;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    Unbinder unbinder;

    private CheckFailAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_fail, container, false);
        unbinder = ButterKnife.bind(this, view);

        srlSwipe.setColorSchemeResources(R.color.app_main);
        srlSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (srlSwipe.isRefreshing()) {
                    srlSwipe.setRefreshing(false);
                }
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(ContextCompat.getColor(getActivity(), R.color.windowBackground));
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).paint(paint).build());

        adapter = new CheckFailAdapter(new ArrayList<CheckFail>(),getActivity());
        rv.setAdapter(adapter);

        isViewCreated = true;
        lazyLoad();
        Logger.i(getClass().getName() + "--onCreateView");
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.i(getClass().getName() + "--isVisibleToUser" + "--" + isVisibleToUser);
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

            List<CheckFail> list = new ArrayList<>();
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());
            list.add(new CheckFail());

            adapter.addAll(list);
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
