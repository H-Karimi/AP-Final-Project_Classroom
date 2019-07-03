package com.classroom.zed.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ClassWorkActivity_RecyclerAdapter_Inner extends RecyclerView.Adapter<ClassWorkActivity_RecyclerAdapter_Inner.MyViewHolder> {

    private Context context;
    private List<String> strings;

    public ClassWorkActivity_RecyclerAdapter_Inner(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classwork_inner_recycler_items, viewGroup, false);
        return new ClassWorkActivity_RecyclerAdapter_Inner.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.classwork_name_tv.setText(strings.get(i).substring(0, 4));
        viewHolder.classwork_date_tv.setText(strings.get(i).substring(4, 8));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView classwork_name_tv;
        private TextView classwork_date_tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            classwork_name_tv = itemView.findViewById(R.id.classwork_name_tv);
            classwork_date_tv = itemView.findViewById(R.id.classwork_date_tv);
        }
    }
}
