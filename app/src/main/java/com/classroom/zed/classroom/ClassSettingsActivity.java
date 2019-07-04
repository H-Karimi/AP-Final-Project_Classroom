package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ClassSettingsActivity extends AppCompatActivity {

    private EditText class_settings_title_et;
    private TextView class_settings_title_error_tv;
    private EditText class_settings_description_et;
    private EditText class_settings_section_et;
    private EditText class_settings_room_et;
    private TextView class_settings_room_error_tv;
    private FrameLayout class_settings_code_frame;
    private TextView class_settings_code_tv;
    private FrameLayout class_settings_stream_frame;
    private TextView class_settings_stream_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Class Settings");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_class_settings);

        class_settings_title_et = findViewById(R.id.class_settings_title_et);
        class_settings_title_error_tv = findViewById(R.id.class_settings_title_error_tv);
        class_settings_description_et = findViewById(R.id.class_settings_description_et);
        class_settings_section_et = findViewById(R.id.class_settings_section_et);
        class_settings_room_et = findViewById(R.id.class_settings_room_et);
        class_settings_room_error_tv = findViewById(R.id.class_settings_room_error_tv);
        class_settings_code_frame = findViewById(R.id.class_settings_code_frame);
        class_settings_code_tv = findViewById(R.id.class_settings_code_tv);
        class_settings_stream_frame = findViewById(R.id.class_settings_stream_frame);
        class_settings_stream_tv = findViewById(R.id.class_settings_stream_tv);


        class_settings_stream_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_class_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!class_settings_title_et.getText().toString().trim().isEmpty() && !class_settings_room_et.getText().toString().trim().isEmpty()) {
            Toast.makeText(ClassSettingsActivity.this, "Changes saved.", Toast.LENGTH_LONG).show();
        }
        else {
            if (class_settings_title_et.getText().toString().trim().isEmpty())
                class_settings_title_error_tv.setText("Class title field cannot be empty.");
            else
                class_settings_title_error_tv.setText("");
            if (class_settings_room_et.getText().toString().trim().isEmpty())
                class_settings_room_error_tv.setText("Room number field cannot be empty.");
            else
                class_settings_room_error_tv.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}
