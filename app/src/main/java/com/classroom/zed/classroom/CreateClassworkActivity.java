package com.classroom.zed.classroom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CreateClassworkActivity extends AppCompatActivity {

    private String input = "";

    private EditText create_classwork_title_et;
    private TextView create_classwork_title_error_tv;
    private EditText create_classwork_description_et;
    private EditText create_classwork_points_et;
    private EditText create_classwork_due_et;
    private EditText create_classwork_topic_et;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Classwork");
        setContentView(R.layout.activity_create_classwork);

        Communicator communicator = new Communicator();
        communicator.execute(getIntent().getExtras().getString("ClassCode") + "@@@AHP");

        create_classwork_title_et = findViewById(R.id.create_classwork_title_et);
        create_classwork_title_error_tv = findViewById(R.id.create_classwork_title_error_tv);
        create_classwork_description_et = findViewById(R.id.create_classwork_description_et);
        create_classwork_points_et = findViewById(R.id.create_classwork_points_et);
        create_classwork_due_et = findViewById(R.id.create_classwork_due_et);
        create_classwork_topic_et = findViewById(R.id.create_classwork_topic_et);

        create_classwork_due_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(CreateClassworkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = String.format("%04d/%02d/%02d", year, month, dayOfMonth);
                        create_classwork_due_et.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_classwork, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Send")) {
            if (!create_classwork_title_et.getText().toString().trim().isEmpty()) {
                create_classwork_title_error_tv.setText("");
                Communicator communicator = new Communicator();

                String date = create_classwork_due_et.getText().toString().trim().equals("NoDueDate") ? "2222/12/22" : create_classwork_due_et.getText().toString().trim();

                communicator.execute("N#" + create_classwork_title_et.getText().toString().trim() + "#" + create_classwork_topic_et.getText().toString() + "#" + create_classwork_description_et.getText().toString().trim() + "#" + create_classwork_points_et.getText().toString() + "#" + date + "%23/59");
                try {
                    input = communicator.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CreateClassworkActivity.this, ClassPageActivity.class);
                intent.putExtra("ClassCode", getIntent().getExtras().getString("ClassCode"));
                intent.putExtra("ClassState", getIntent().getExtras().getString("ClassState"));
                startActivity(intent);
            } else
                create_classwork_title_error_tv.setText("Title field cannot be empty.");
        } else if (item.getTitle().equals("Attach")) {

        }
        return super.onOptionsItemSelected(item);
    }
}
