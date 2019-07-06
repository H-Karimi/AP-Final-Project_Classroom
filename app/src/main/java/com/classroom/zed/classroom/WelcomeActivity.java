package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button signin_b;
    Button signup_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        signin_b = findViewById(R.id.signin_b);
        signup_b = findViewById(R.id.signup_b);

        signin_b.setOnClickListener((view) ->{
            Intent intent = new Intent(WelcomeActivity.this, SigninActivity.class);
            startActivity(intent);
        });

        signup_b.setOnClickListener((view) ->{
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
