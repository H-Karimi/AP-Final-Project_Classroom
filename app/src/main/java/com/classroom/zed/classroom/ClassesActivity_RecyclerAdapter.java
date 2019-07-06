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

public class ClassesActivity_RecyclerAdapter extends RecyclerView.Adapter<ClassesActivity_RecyclerAdapter.MyViewHolder> implements PopupMenu.OnMenuItemClickListener {
    private String input;

    private Context context;
    private List<String> strings;

    public ClassesActivity_RecyclerAdapter(Context context, List<String> strings){
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.classes_recycler_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        String s = strings.get(i);
        viewHolder.className_tv.setText(s.split("#")[0]);
        viewHolder.classSubject_tv.setText(s.split("#")[1]);
        viewHolder.teacherName_tv.setText(s.split("#")[2].split(";")[0]);
        viewHolder.classes_code_tv.setText(s.split("#")[3]);
        viewHolder.classes_state_tv.setText(s.split("#")[4]);


        viewHolder.classes_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.setOnMenuItemClickListener(ClassesActivity_RecyclerAdapter.this);
                if (viewHolder.classes_state_tv.getText().toString().trim().equals("S"))
                    popupMenu.inflate(R.menu.menu_classes_popup_student);
                else if (viewHolder.classes_state_tv.getText().toString().trim().equals("T"))
                    popupMenu.inflate(R.menu.menu_classes_popup_teacher);

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.classes_unenroll){
                            Communicator communicator = new Communicator();
                            communicator.execute("Q#" + viewHolder.classes_code_tv.getText().toString());
                            try {
                                input = communicator.get();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            if(input.equals("C#0")){
                                viewHolder.classes_cardView.setVisibility(View.GONE);
                                Toast.makeText(context,"Class removed", Toast.LENGTH_LONG).show();
                            }
                            return true;
                        }
                        else if(menuItem.getItemId() == R.id.classes_classSettings){
                            Toast.makeText(context, "Class settings " + i, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        viewHolder.classes_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassPageActivity.class);
                intent.putExtra("ClassCode", viewHolder.classes_code_tv.getText().toString());
                Log.d("CODE_", viewHolder.classes_code_tv.getText().toString());
                intent.putExtra("ClassState", viewHolder.classes_state_tv.getText().toString());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView className_tv;
        private TextView classSubject_tv;
        private TextView teacherName_tv;
        private TextView classes_code_tv;
        private TextView classes_state_tv;
        private ImageView classes_iv;

        private CardView classes_cardView;

        private MenuItem classes_unenroll;
        private MenuItem classes_classSettings;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            className_tv = itemView.findViewById(R.id.className_tv);
            classSubject_tv = itemView.findViewById(R.id.classSubject_tv);
            teacherName_tv = itemView.findViewById(R.id.teacherName_tv);
            classes_code_tv = itemView.findViewById(R.id.classes_code_tv);
            classes_state_tv = itemView.findViewById(R.id.classes_state_tv);
            classes_iv = itemView.findViewById(R.id.classes_iv);
            classes_cardView = itemView.findViewById(R.id.classes_cardView);
            classes_unenroll = itemView.findViewById(R.id.classes_unenroll);
            classes_classSettings = itemView.findViewById(R.id.classes_classSettings);

        }

        public TextView getClasses_code_tv() {
            return classes_code_tv;
        }
    }

}
