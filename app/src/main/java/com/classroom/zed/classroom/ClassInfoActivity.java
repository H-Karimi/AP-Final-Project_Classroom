package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClassInfoActivity extends AppCompatActivity {
    private String input = "";

    private TextView class_info_name_tv;
    private TextView class_info_description_tv;
    private TextView class_info_room_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_class_info);

        Communicator communicator = new Communicator();
        communicator.execute(getIntent().getExtras().getString("ClassCode") + "@@@CIP");
        try {
            input = communicator.get();
        }catch (Exception e){
            e.printStackTrace();
        }


        class_info_name_tv = findViewById(R.id.class_info_name_tv);
        class_info_description_tv = findViewById(R.id.class_info_description_tv);
        class_info_room_tv = findViewById(R.id.class_info_room_tv);

        class_info_name_tv.setText(input.split("~")[0]);
        class_info_description_tv.setText(input.split("~")[2]);
        if(class_info_description_tv.getText().toString().trim().isEmpty())
            class_info_description_tv.setVisibility(View.GONE);
        else
            class_info_description_tv.setVisibility(View.VISIBLE);
        class_info_room_tv.setText(input.split("~")[1]);

    }
}
