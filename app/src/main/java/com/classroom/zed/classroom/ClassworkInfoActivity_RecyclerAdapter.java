package com.classroom.zed.classroom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ClassworkInfoActivity_RecyclerAdapter extends RecyclerView.Adapter<ClassworkInfoActivity_RecyclerAdapter.MyViewHolder> {
    private String input;

    private Context context;
    private String[] strings;
    private String state;
    private String points;

    public ClassworkInfoActivity_RecyclerAdapter(Context context, String[] strings, String state, String points) {
        this.context = context;
        this.strings = strings;
        this.state = state;
        this.points = points;
    }

    @NonNull
    @Override
    public ClassworkInfoActivity_RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classwork_info_recycler_items, viewGroup, false);
        return new ClassworkInfoActivity_RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassworkInfoActivity_RecyclerAdapter.MyViewHolder viewHolder, int i) {

        viewHolder.classwork_info_studentName_tv.setText(strings[i].split("!")[0]);
        if (strings[i].split("!")[1].equals("null") || strings[i].split("!")[1] == null || strings[i].split("!")[1].equals("-1"))
            viewHolder.classwork_info_points_tv.setText("Unassigned");
        else
            viewHolder.classwork_info_points_tv.setText(strings[i].split("!")[1] + "/" + points);

    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView classwork_info_studentName_tv;
        private TextView classwork_info_points_tv;
        private ImageView classwork_image_iv;

        private CardView classwork_info_cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            classwork_info_studentName_tv = itemView.findViewById(R.id.classwork_info_studentName_tv);
            classwork_info_points_tv = itemView.findViewById(R.id.classwork_info_points_tv);
            classwork_image_iv = itemView.findViewById(R.id.classwork_image_iv);
            classwork_info_cardview = itemView.findViewById(R.id.classwork_info_carview);
        }
    }
}
