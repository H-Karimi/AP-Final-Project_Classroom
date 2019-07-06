package com.classroom.zed.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PeopleFragment_RecyclerAdapter_Inner extends RecyclerView.Adapter<PeopleFragment_RecyclerAdapter_Inner.MyViewHolder> {

    private Context context;
    private List<String> strings;
    private ClassInfo classInfo;
    public PeopleFragment_RecyclerAdapter_Inner(Context context, List<String> strings, ClassInfo classInfo){
        this.context = context;
        this.strings = strings;
        this.classInfo = classInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_inner_recycler_items, viewGroup, false);
        return new PeopleFragment_RecyclerAdapter_Inner.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.people_name_tv.setText(strings.get(i));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView people_image_iv;
        private TextView people_name_tv;
        private ImageView people_menu_iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            people_image_iv = itemView.findViewById(R.id.people_image_iv);
            people_name_tv = itemView.findViewById(R.id.people_name_tv);
            people_menu_iv = itemView.findViewById(R.id.people_menu_iv);
        }
    }
}
