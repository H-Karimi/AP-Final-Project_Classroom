package com.classroom.zed.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment_RecyclerAdapter_Outer extends RecyclerView.Adapter<PeopleFragment_RecyclerAdapter_Outer.MyViewHolder> {

    private Context context;
    private List<String> strings;
    private ClassInfo classInfo;

    private LinearLayoutManager linearLayoutManager;
    private PeopleFragment_RecyclerAdapter_Inner peopleFragment_recyclerAdapter_inner;

    public PeopleFragment_RecyclerAdapter_Outer(Context context, List<String> strings, ClassInfo classInfo) {
        this.context = context;
        this.strings = strings;
        this.classInfo = classInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_outer_recycler_items, viewGroup, false);
        return new PeopleFragment_RecyclerAdapter_Outer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.people_title_tv.setText(strings.get(i).split("#")[0]);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        peopleFragment_recyclerAdapter_inner = new PeopleFragment_RecyclerAdapter_Inner(this.context, getList(strings.get(i)), classInfo);
        viewHolder.people_inner_recycler_view.setLayoutManager(linearLayoutManager);
        viewHolder.people_inner_recycler_view.setAdapter(peopleFragment_recyclerAdapter_inner);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView people_title_tv;
        private RecyclerView people_inner_recycler_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            people_title_tv = itemView.findViewById(R.id.people_title_tv);
            people_inner_recycler_view = itemView.findViewById(R.id.people_inner_recycler_view);
        }
    }


    private List<String> getList(String input){
        List<String> list = new ArrayList<>();
        input = input.substring(input.indexOf('#')+1);
        for (int i = 0; i < input.split("#").length; i++) {
            list.add(input.split("#")[i]);
        }
        return list;
    }
}
