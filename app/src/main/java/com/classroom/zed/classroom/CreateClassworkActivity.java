package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class CreateClassworkActivity extends AppCompatActivity {

    private EditText create_classwork_title_et;
    private EditText create_classwork_description_et;
    private EditText create_classwork_points_et;
    private EditText create_classwork_due_et;
    private EditText create_classwork_topic_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Classwork");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_create_classwork);

        create_classwork_title_et = findViewById(R.id.create_classwork_title_et);
        create_classwork_description_et = findViewById(R.id.create_classwork_description_et);
        create_classwork_points_et = findViewById(R.id.create_classwork_points_et);
        create_classwork_due_et = findViewById(R.id.create_classwork_due_et);
        create_classwork_topic_et = findViewById(R.id.create_classwork_topic_et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_classwork, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Send")){

        }
        else if(item.getTitle().equals("Attach")){

        }
        return super.onOptionsItemSelected(item);
    }
}
