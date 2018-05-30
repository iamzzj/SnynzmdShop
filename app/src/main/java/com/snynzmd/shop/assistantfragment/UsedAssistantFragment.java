package com.snynzmd.shop.assistantfragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiang.android.lib.adapter.expand.StickyRecyclerHeadersDecoration;
import com.orhanobut.logger.Logger;
import com.snynzmd.shop.R;
import com.snynzmd.shop.adapter.AssistantAdapter;
import com.snynzmd.shop.entity.Assistant;
import com.snynzmd.shop.utils.CharacterParser;
import com.snynzmd.shop.utils.PinyinComparator;
import com.snynzmd.shop.view.HorizontalDividerItemDecoration;
import com.snynzmd.shop.widget.SideBar;
import com.snynzmd.shop.widget.TouchableRecyclerView;
import com.snynzmd.shop.widget.ZSideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by z on 2018/3/2.
 */

public class UsedAssistantFragment extends Fragment {
    @BindView(R.id.trv_assistant_contact_member)
    TouchableRecyclerView trvAssistantContactMember;
    @BindView(R.id.zsb_assistant_contact_zsidebar)
    ZSideBar zsbAssistantContactZsidebar;

    private Unbinder unBinder;

    private List<Assistant> list = new ArrayList<>();
    private AssistantAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_used_assistant, container, false);
        unBinder = ButterKnife.bind(this, view);

        adapter = new AssistantAdapter(list, getActivity());
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        trvAssistantContactMember.setLayoutManager(linearLayoutManager);
        final Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(ContextCompat.getColor(getActivity(), R.color.assistant_header));
        trvAssistantContactMember.addItemDecoration(headersDecor);
        trvAssistantContactMember.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).paint(paint).build());
        trvAssistantContactMember.setAdapter(adapter);

        zsbAssistantContactZsidebar.setupWithRecycler(trvAssistantContactMember);

        list.add(new Assistant("程浩"));
        list.add(new Assistant("蒋自全"));
        list.add(new Assistant("李耀平"));
        list.add(new Assistant("李治隆"));
        list.add(new Assistant("区毅凡"));
        list.add(new Assistant("任春清"));
        list.add(new Assistant("苏汉健"));
        list.add(new Assistant("孙媛"));
        list.add(new Assistant("王涛"));
        list.add(new Assistant("谭倩文"));
        list.add(new Assistant("吴秋发"));
        list.add(new Assistant("徐萍"));
        list.add(new Assistant("杨阳新"));
        list.add(new Assistant("余维爱"));
        list.add(new Assistant("余春霞"));
        list.add(new Assistant("曾木荣"));
        list.add(new Assistant("周锋"));
        list.add(new Assistant("张如意"));
        list.add(new Assistant("章建"));
        list.add(new Assistant("🎶"));
        list.add(new Assistant("曾工"));
        list.add(new Assistant("木工"));
        list.add(new Assistant("fs"));
        list.add(new Assistant("$$"));

        CharacterParser characterParser = new CharacterParser();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Assistant assistant = list.get(i);
                String pinyin = characterParser.getSelling(list.get(i).getName());
                if (!TextUtils.isEmpty(pinyin)) {
                    String sortString = pinyin.substring(0, 1).toUpperCase();

                    if (sortString.matches("[A-Z]")) {
                        assistant.setSortLetters(sortString.toUpperCase());
                    } else {
                        assistant.setSortLetters("#");
                    }
                } else {
                    assistant.setSortLetters("#");
                }
            }
            Collections.sort(list, new PinyinComparator());
        }

        adapter.addAll(list);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unBinder != null) {
            unBinder.unbind();
        }
    }
}
