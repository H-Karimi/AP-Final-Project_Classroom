package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {
    private String input = "";

    private TextView about_us_description_tv;
    private TextView about_us_email_tv;
    private TextView about_us_student_code_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About Us");
        setContentView(R.layout.activity_about_us);

        about_us_description_tv = findViewById(R.id.about_us_description_tv);
        about_us_email_tv = findViewById(R.id.about_us_email_tv);
        about_us_student_code_tv = findViewById(R.id.about_us_student_code_tv);

        Communicator communicator = new Communicator();
        communicator.execute("@@@AUP");
        try {
            input = communicator.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        about_us_description_tv.setText(input.split("#")[0]);
        about_us_email_tv.setText(input.split("#")[1]);
        about_us_student_code_tv.setText(input.split("#")[2]);

    }
}
