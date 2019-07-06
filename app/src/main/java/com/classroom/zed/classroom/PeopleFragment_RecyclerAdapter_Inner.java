package com.classroom.zed.classroom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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

    public PeopleFragment_RecyclerAdapter_Inner(Context context, List<String> strings, ClassInfo classInfo) {
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

        myViewHolder.people_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("ClassCode", classInfo.getCode());
                intent.putExtra("Username", myViewHolder.people_name_tv.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView people_image_iv;
        private TextView people_name_tv;
        private ImageView people_menu_iv;
        private CardView people_cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            people_image_iv = itemView.findViewById(R.id.people_image_iv);
            people_name_tv = itemView.findViewById(R.id.people_name_tv);
            people_menu_iv = itemView.findViewById(R.id.people_menu_iv);
            people_cardview = itemView.findViewById(R.id.people_cardview);
        }
    }
}
