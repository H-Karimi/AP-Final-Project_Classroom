package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClassworkInfoActivity extends AppCompatActivity {
    private String input = "";

    private TextView classwork_info_name_tv;
    private TextView classwork_info_description_tv;
    private TextView classwork_info_topic_tv;
    private TextView classwork_info_date_tv;
    private RecyclerView classwork_info_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Classwork Info");
        setContentView(R.layout.activity_classwork_info);

        classwork_info_name_tv = findViewById(R.id.classwork_info_name_tv);
        classwork_info_description_tv = findViewById(R.id.classwork_info_description_tv);
        classwork_info_topic_tv = findViewById(R.id.classwork_info_topic_tv);
        classwork_info_date_tv = findViewById(R.id.classwork_info_date_tv);
        classwork_info_recyclerview = findViewById(R.id.classwork_info_recyclerview);

        Communicator communicator = new Communicator();
        communicator.execute(getIntent().getExtras().getString("ClassCode") + "@" + getIntent().getExtras().getString("ClassworkCode") + "@@HWP");
        try {
            input = communicator.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String classwork = input.split("#")[1];
        classwork_info_name_tv.setText(classwork.split("~")[0]);
        classwork_info_topic_tv.setText(classwork.split("~")[1]);
        classwork_info_description_tv.setText(classwork.split("~")[2]);
        if (classwork.split("~")[3].split("%")[0].equals("2222/12/22"))
            classwork_info_date_tv.setText("No due date");
        else
            classwork_info_date_tv.setText(classwork.split("~")[3].split("%")[0]);

        if (input.split("#")[0].equals("T") && !input.split("#")[3].isEmpty()) {
            classwork_info_recyclerview.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            ClassworkInfoActivity_RecyclerAdapter classworkInfoActivity_recyclerAdapter = new ClassworkInfoActivity_RecyclerAdapter(this, input.split("#")[3].split("~"), input.split("#")[0], input.split("#")[2]);
            classwork_info_recyclerview.setLayoutManager(linearLayoutManager);
            classwork_info_recyclerview.setAdapter(classworkInfoActivity_recyclerAdapter);
            classwork_info_recyclerview.setItemAnimator(new DefaultItemAnimator());
        } else if (input.split("#")[0].equals("S") || input.split("#")[3].isEmpty()) {
            classwork_info_recyclerview.setVisibility(View.GONE);
        }

        if (classwork_info_description_tv.getText().toString().trim().isEmpty())
            classwork_info_description_tv.setVisibility(View.GONE);
        else
            classwork_info_description_tv.setVisibility(View.VISIBLE);
    }


    private List<String> getList() {
        List<String> list = new ArrayList<>();


        return list;
    }
}
