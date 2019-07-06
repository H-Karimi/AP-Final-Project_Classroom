package com.classroom.zed.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProfileActivity_RecyclerAdapter extends RecyclerView.Adapter<ProfileActivity_RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<String> strings;

    public ProfileActivity_RecyclerAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_recycler_items, viewGroup, false);
        return new ProfileActivity_RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.profile_classwork_name_tv.setText(strings.get(i).split("~")[0]);

        if (strings.get(i).split("~")[2].split("%")[0].equals("2222/22/22"))
            myViewHolder.profile_classwork_date_tv.setText("No due date");
        else
            myViewHolder.profile_classwork_date_tv.setText(strings.get(i).split("~")[2].split("%")[0]);

        if (strings.get(i).split("~")[1].equals("-1") || strings.get(i).split("~")[1].equals("null") || strings.get(i).split("~")[1] == null) {
            myViewHolder.profile_classwork_score_tv.setVisibility(View.GONE);
            myViewHolder.profile_classwork_state_tv.setText("Unassigned");
        } else {
            myViewHolder.profile_classwork_score_tv.setText(strings.get(i).split("~")[1]);
            myViewHolder.profile_classwork_state_tv.setText("Not turned in");
        }

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView profile_classwork_name_tv;
        private TextView profile_classwork_date_tv;
        private TextView profile_classwork_score_tv;
        private TextView profile_classwork_state_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_classwork_name_tv = itemView.findViewById(R.id.profile_classwork_name_tv);
            profile_classwork_date_tv = itemView.findViewById(R.id.profile_classwork_date_tv);
            profile_classwork_score_tv = itemView.findViewById(R.id.profile_classwork_score_tv);
            profile_classwork_state_tv = itemView.findViewById(R.id.profile_classwork_state_tv);
        }
    }
}
