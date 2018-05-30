package com.snynzmd.shop.assistantfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snynzmd.shop.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by z on 2018/3/2.
 */

public class UnUsedAssistantFragment extends Fragment{
    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unused_assistant,container,false);
        unBinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(unBinder!=null){
            unBinder.unbind();
        }
    }
}
