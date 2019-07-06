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

    private String output = "";
    private String input = "";

    private EditText class_settings_title_et;
    private TextView class_settings_title_error_tv;
    private EditText class_settings_description_et;
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
        setContentView(R.layout.activity_class_settings);

        Communicator communicator = new Communicator();
        communicator.execute(getIntent().getExtras().getString("ClassCode") + "@@@COP");
        try {
            input = communicator.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        class_settings_title_et = findViewById(R.id.class_settings_title_et);
        class_settings_title_error_tv = findViewById(R.id.class_settings_title_error_tv);
        class_settings_description_et = findViewById(R.id.class_settings_description_et);
        class_settings_room_et = findViewById(R.id.class_settings_room_et);
        class_settings_room_error_tv = findViewById(R.id.class_settings_room_error_tv);
        class_settings_code_frame = findViewById(R.id.class_settings_code_frame);
        class_settings_code_tv = findViewById(R.id.class_settings_code_tv);
        class_settings_stream_frame = findViewById(R.id.class_settings_stream_frame);
        class_settings_stream_tv = findViewById(R.id.class_settings_stream_tv);


        class_settings_title_et.setText(input.split("~")[0]);
        class_settings_code_tv.setText("Class code: " + input.split("~")[1]);
        class_settings_description_et.setText(input.split("~")[2]);
        class_settings_room_et.setText(input.split("~")[3]);
        switch (input.split("~")[4]) {
            case "B":
                class_settings_stream_tv.setText("Teachers and students can leave comment.");
                break;
            case "C":
                class_settings_stream_tv.setText("Only teachers can leave comment.");
                break;
            case "N":
                class_settings_stream_tv.setText("No comment is permitted.");
                break;
        }


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
            output = class_settings_title_et.getText().toString().trim() + "#" +
                    class_settings_description_et.getText().toString().trim() + "#" +
                    class_settings_room_et.getText().toString().trim() + "#";
            if (class_settings_stream_tv.getText().toString().charAt(0) == 'T')
                output += "B";
            if (class_settings_stream_tv.getText().toString().charAt(0) == 'O')
                output += "C";
            if (class_settings_stream_tv.getText().toString().charAt(0) == 'N')
                output += "N";

            Communicator communicator = new Communicator();
            communicator.execute(output);
            try {
                input = communicator.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(ClassSettingsActivity.this, "Changes saved.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ClassSettingsActivity.this, ClassPageActivity.class);
            intent.putExtra("ClassCode", class_settings_code_tv.getText().toString().replace("Class code: ", ""));
            intent.putExtra("ClassState", "T");
            startActivity(intent);
        } else {
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
