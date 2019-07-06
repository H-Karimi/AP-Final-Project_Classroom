package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SigninActivity extends AppCompatActivity {

    private String output = "";
    private String input = "";

    private EditText username_et;
    private EditText password_et;
    private TextView usernameError_tv;
    private TextView passwordError_tv;
    private Button signin_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign in");
        setContentView(R.layout.activity_signin);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        usernameError_tv = findViewById(R.id.usernameError_tv);
        passwordError_tv = findViewById(R.id.passwordError_tv);
        signin_b = findViewById(R.id.signin_b);

        username_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (username_et.getText().toString().trim().equals(""))
                        usernameError_tv.setText("Username field cannot be empty.");
                    else
                        usernameError_tv.setText("");
                } else {
                    usernameError_tv.setText("");
                }
            }
        });

        password_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (password_et.getText().toString().trim().equals(""))
                        passwordError_tv.setText("Password field cannot be empty.");
                    else
                        passwordError_tv.setText("");
                } else {
                    passwordError_tv.setText("");
                }
            }
        });

        signin_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username_et.getText().toString().trim().equals("") && !password_et.getText().toString().trim().equals("")) {
                    output = "L#" + username_et.getText().toString().trim() + "#" + password_et.getText().toString().trim();
                    Communicator communicator = new Communicator();
                    communicator.execute(output);
                    try {
                        input = communicator.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    if (input.equals("E#1")) {
                        usernameError_tv.setText("Username not found.");
                    } else if (input.equals("E#2")) {
                        passwordError_tv.setText("Password is incorrect");
                    } else if (input.equals("E#0")) {
                        Toast.makeText(SigninActivity.this, "Signed in", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SigninActivity.this, ClassesActivity.class);
                        startActivity(intent);
                    }
                } else {
                    if (password_et.getText().toString().trim().equals(""))
                        passwordError_tv.setText("Password field cannot be empty.");
                    else
                        passwordError_tv.setText("");
                    if (username_et.getText().toString().trim().equals(""))
                        usernameError_tv.setText("Username field cannot be empty.");
                    else
                        usernameError_tv.setText("");
                }
            }
        });
    }
}
