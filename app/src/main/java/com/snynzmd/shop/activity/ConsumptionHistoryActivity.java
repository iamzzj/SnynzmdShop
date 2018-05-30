package com.snynzmd.shop.activity;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.bigkoo.pickerview.TimePickerView;
import com.gyf.barlibrary.ImmersionBar;
import com.snynzmd.shop.R;
import com.snynzmd.shop.adapter.ConsumptionHistoryAdapter;
import com.snynzmd.shop.entity.ConsumptionHistory;
import com.snynzmd.shop.view.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumptionHistoryActivity extends AppCompatActivity {
    @BindView(R.id.v_consumption_history_bar)
    View vConsumptionHistoryBar;
    @BindView(R.id.ctl_consumption_history_manage)
    CollapsingToolbarLayout ctlConsumptionHistoryManage;
    @BindView(R.id.rv_consumption_history)
    RecyclerView rv;
    @BindView(R.id.srl_consumption_history_swipe)
    SwipeRefreshLayout srlSwipe;
    private ImmersionBar immersionBar;

    private ConsumptionHistoryAdapter adapter;
    private TimePickerView tpv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);
        ButterKnife.bind(this);

        immersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.white)  //修改flyme OS状态栏字体颜色
                .fitsSystemWindows(false)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        immersionBar.init();  //必须调用方可沉浸式

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vConsumptionHistoryBar.setVisibility(View.VISIBLE);
        }

        //下拉一瞬间颜色
        ctlConsumptionHistoryManage.setContentScrimResource(R.drawable.app_main_top);

        srlSwipe.setColorSchemeResources(R.color.app_main);
        srlSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (srlSwipe.isRefreshing()) {
                    srlSwipe.setRefreshing(false);
                }
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(ContextCompat.getColor(this, R.color.windowBackground));
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());
        //rv.setNestedScrollingEnabled(false);

        adapter = new ConsumptionHistoryAdapter(new ArrayList<ConsumptionHistory>(), this);
        rv.setAdapter(adapter);

        tpv = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //tvTime.setText(TimeUtils.formatDate(date));


            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "", "", "", "")
                .setSubmitColor(ContextCompat.getColor(this, R.color.app_main))
                .setCancelColor(ContextCompat.getColor(this, R.color.app_main))
                .isCenterLabel(false)
                .isDialog(false)
                .build();

        List<ConsumptionHistory> list = new ArrayList<>();
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        list.add(new ConsumptionHistory());
        adapter.addAll(list);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (immersionBar != null)
            immersionBar.destroy();
    }

    @OnClick(R.id.ll_consumption_history_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.iv_consumption_history_time)
    public void time() {
        if(tpv!=null){
            tpv.show();
        }
    }
}
