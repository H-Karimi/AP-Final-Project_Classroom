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
        ClassWorkActivity_RecyclerAdapter_Outer adapter = new ClassWorkActivity_RecyclerAdapter_Outer(rootView.getContext(), getList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("OOP#Assignment1#Date1#Assignment2#Date2");
        list.add("Socket#Assignment3#Date3#Assignment4#Date4");
        list.add("IO#Assignment5#Date5#Assignment6#Date6");
        list.add("Lorem#Assignment7#Date7#Assignment8#Date8");
        return list;
    }
}
