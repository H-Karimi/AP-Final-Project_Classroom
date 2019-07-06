package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClassworkInfoActivity extends AppCompatActivity {
    private String input = "";

    private TextView classwork_info_name_tv;
    private TextView classwork_info_description_tv;
    private TextView classwork_info_topic_tv;
    private TextView classwork_info_date_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Classwork Info");
        setContentView(R.layout.activity_classwork_info);

        classwork_info_name_tv = findViewById(R.id.classwork_info_name_tv);
        classwork_info_description_tv = findViewById(R.id.classwork_info_description_tv);
        classwork_info_topic_tv = findViewById(R.id.classwork_info_topic_tv);
        classwork_info_date_tv = findViewById(R.id.classwork_info_date_tv);
    }
}
