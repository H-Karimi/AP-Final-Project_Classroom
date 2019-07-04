package com.classroom.zed.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ClassesActivity_RecyclerAdapter extends RecyclerView.Adapter<ClassesActivity_RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<String> strings;
    private OnItemListener onItemListener;

    public ClassesActivity_RecyclerAdapter(Context context, List<String> strings, OnItemListener onItemListener){
        this.context = context;
        this.strings = strings;
        this.onItemListener = onItemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classes_recycler_items, viewGroup, false);
        return new MyViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.className_tv.setText(strings.get(i).split("#")[0]);
        viewHolder.classSubject_tv.setText(strings.get(i).split("#")[1]);
        viewHolder.teacherName_tv.setText(strings.get(i).split("#")[2]);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView className_tv;
        private TextView classSubject_tv;
        private TextView teacherName_tv;

        OnItemListener onItemListener;
        public MyViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            className_tv = itemView.findViewById(R.id.className_tv);
            classSubject_tv = itemView.findViewById(R.id.classSubject_tv);
            teacherName_tv = itemView.findViewById(R.id.teacherName_tv);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    interface OnItemListener{
        void onItemClick(int position);
    }
}
