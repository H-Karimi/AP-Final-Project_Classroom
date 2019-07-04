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

public class PeopleFragment_RecyclerAdapter_Outer extends RecyclerView.Adapter<PeopleFragment_RecyclerAdapter_Outer.MyViewHolder> {

    private Context context;
    private List<String> strings;

    private LinearLayoutManager linearLayoutManager;
    private PeopleFragment_RecyclerAdapter_Inner peopleFragment_recyclerAdapter_inner;

    public PeopleFragment_RecyclerAdapter_Outer(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_outer_recycler_items, viewGroup, false);
        return new PeopleFragment_RecyclerAdapter_Outer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.people_title_tv.setText(strings.get(i));
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        peopleFragment_recyclerAdapter_inner = new PeopleFragment_RecyclerAdapter_Inner(this.context, getList());
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


    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("Asghar Asghari");
        list.add("Mamad Mamadi");
        list.add("Jafar Jafari");
        list.add("Abbas Abbasi");
        list.add("Asghar Asghari");
        list.add("Mamad Mamadi");
        list.add("Jafar Jafari");
        list.add("Abbas Abbasi");
        return list;
    }
}
