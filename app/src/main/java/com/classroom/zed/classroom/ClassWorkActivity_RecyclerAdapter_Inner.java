package com.classroom.zed.classroom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassWorkActivity_RecyclerAdapter_Inner extends RecyclerView.Adapter<ClassWorkActivity_RecyclerAdapter_Inner.MyViewHolder> {

    private Context context;
    private String[] strings;
    private ClassInfo classInfo;

    public ClassWorkActivity_RecyclerAdapter_Inner(Context context, String[] strings, ClassInfo classInfo) {
        this.context = context;
        this.strings = strings;
        this.classInfo = classInfo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classwork_inner_recycler_items, viewGroup, false);
        return new ClassWorkActivity_RecyclerAdapter_Inner.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.classwork_name_tv.setText(strings[i + 1].split(":")[0]);
        myViewHolder.classwork_code_tv.setText(strings[i + 1].split(":")[3]);

        if (classInfo.getState().equals("S")) {
            myViewHolder.classwork_more_iv.setVisibility(View.GONE);
            myViewHolder.classwork_date_tv.setText(strings[i + 1].split(":")[2].split("%")[0]);
            if (myViewHolder.classwork_date_tv.getText().toString().equals("2222/12/22"))
                myViewHolder.classwork_date_tv.setText("No due date");
        } else if (classInfo.getState().equals("T")) {
            myViewHolder.classwork_more_iv.setVisibility(View.VISIBLE);
            myViewHolder.classwork_date_tv.setText("Posted on " + strings[i + 1].split(":")[1].split("%")[1].replace('/', ':'));
        }

        myViewHolder.classwork_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassworkInfoActivity.class);
                intent.putExtra("ClassworkCode", myViewHolder.classwork_code_tv.getText().toString());
                intent.putExtra("ClassCode", classInfo.getCode());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.length - 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView classwork_name_tv;
        private TextView classwork_date_tv;
        private TextView classwork_code_tv;
        private ImageView classwork_more_iv;
        private CardView classwork_cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            classwork_name_tv = itemView.findViewById(R.id.classwork_name_tv);
            classwork_date_tv = itemView.findViewById(R.id.classwork_date_tv);
            classwork_code_tv = itemView.findViewById(R.id.classwork_code_tv);
            classwork_more_iv = itemView.findViewById(R.id.classwork_more_iv);
            classwork_cardview = itemView.findViewById(R.id.classwork_cardview);
        }
    }
}
