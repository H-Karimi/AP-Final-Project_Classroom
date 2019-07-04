package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClassInfoActivity extends AppCompatActivity {
    private TextView class_info_name_tv;
    private TextView class_info_description_tv;
    private TextView class_info_section_tv;
    private TextView class_info_room_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_class_info);

        class_info_name_tv = findViewById(R.id.class_info_name_tv);
        class_info_description_tv = findViewById(R.id.class_info_description_tv);
        class_info_section_tv = findViewById(R.id.class_info_section_tv);
        class_info_room_tv = findViewById(R.id.class_info_room_tv);
    }
}
