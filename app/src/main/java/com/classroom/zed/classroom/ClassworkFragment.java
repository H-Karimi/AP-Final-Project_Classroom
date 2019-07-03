package com.classroom.zed.classroom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ClassworkFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_classwork, null);

        recyclerView = rootView.findViewById(R.id.classwork_outer_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        ClassWorkActivity_RecyclerAdapter_Outer classWorkActivity_recyclerAdapter_outer = new ClassWorkActivity_RecyclerAdapter_Outer(rootView.getContext(), getList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(classWorkActivity_recyclerAdapter_outer);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("asdfghjk");
        list.add("12345678");
        list.add("qwertyui");
        list.add("zxcvbnm,");
        return list;
    }
}
