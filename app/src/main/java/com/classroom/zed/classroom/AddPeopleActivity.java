package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPeopleActivity extends AppCompatActivity {
    private String input;

    private EditText add_people_et;
    private TextView add_people_tv;
    private TextView add_people_error_tv;

    private ClassInfo classInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add participants");
        setContentView(R.layout.activity_add_people);

        classInfo = new ClassInfo(getIntent().getExtras().getString("ClassCode"), getIntent().getExtras().getString("State"));

        add_people_et = findViewById(R.id.add_people_et);
        add_people_tv = findViewById(R.id.add_people_tv);
        add_people_error_tv = findViewById(R.id.add_people_error_tv);

        if (classInfo.getState().equals("S"))
            add_people_tv.setText("Enter a username to add as a student.");
        else if (classInfo.getState().equals("T"))
            add_people_tv.setText("Enter a username to add as a teacher.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_people, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!add_people_et.getText().toString().trim().isEmpty()) {
            add_people_error_tv.setText("");

            Communicator communicator = new Communicator();
            communicator.execute(classInfo.getState() + "#" + add_people_et.getText());
            try {
                input = communicator.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (input.equals("E#0")) {
                if (classInfo.getState().equals("S"))
                    Toast.makeText(this, "User is now a student in your class", Toast.LENGTH_LONG).show();
                else if (classInfo.getState().equals("T"))
                    Toast.makeText(this, "User is now a teacher in your class", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddPeopleActivity.this, ClassPageActivity.class);
                intent.putExtra("ClassCode", classInfo.getCode());
                intent.putExtra("ClassState", classInfo.getState());
                startActivity(intent);
            } else if (input.equals("E#1")) {
                add_people_error_tv.setText("Username not found");
            } else if (input.equals("E#2")) {
                add_people_error_tv.setText("This user is already teacher in the class!");
            } else if (input.equals("E#3")) {
                add_people_error_tv.setText("This user is already student in the class!");
            }
        } else {
            add_people_error_tv.setText("Username field cannot be empty.");
        }

        return super.onOptionsItemSelected(item);
    }
}
