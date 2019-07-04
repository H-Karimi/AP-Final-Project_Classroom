package com.classroom.zed.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClassWorkActivity_RecyclerAdapter_Outer extends RecyclerView.Adapter<ClassWorkActivity_RecyclerAdapter_Outer.MyViewHolder> {

    private Context context;
    private List<String> strings;

    private LinearLayoutManager linearLayoutManager;
    private ClassWorkActivity_RecyclerAdapter_Inner classWorkActivity_recyclerAdapter_inner;

    public ClassWorkActivity_RecyclerAdapter_Outer(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classwork_outer_recycler_items, viewGroup, false);
        return new ClassWorkActivity_RecyclerAdapter_Outer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.classwork_title_tv.setText(strings.get(i).split("#")[0]);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        classWorkActivity_recyclerAdapter_inner = new ClassWorkActivity_RecyclerAdapter_Inner(this.context, strings.get(i).split("#"));
        viewHolder.classwork_inner_recycler_view.setLayoutManager(linearLayoutManager);
        viewHolder.classwork_inner_recycler_view.setAdapter(classWorkActivity_recyclerAdapter_inner);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView classwork_title_tv;
        private RecyclerView classwork_inner_recycler_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            classwork_title_tv = itemView.findViewById(R.id.classwork_title_tv);
            classwork_inner_recycler_view = itemView.findViewById(R.id.classwork_inner_recycler_view);
        }
    }

}
