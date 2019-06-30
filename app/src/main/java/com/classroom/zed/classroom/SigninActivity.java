package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {
    EditText username_et;
    EditText password_et;
    TextView usernameError_tv;
    TextView passwordError_tv;
    Button signin_b;

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
                if(!hasFocus){
                    if (username_et.getText().toString().trim().equals(""))
                        usernameError_tv.setText("Username field cannot be empty.");
                    else
                        usernameError_tv.setText("");
                }
            }
        });

        password_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (password_et.getText().toString().trim().equals(""))
                        passwordError_tv.setText("Password field cannot be empty.");
                    else
                        passwordError_tv.setText("");
                }
            }
        });

        signin_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username_et.getText().toString().trim().equals("")  &&  !password_et.getText().toString().trim().equals("")) {
                    Intent intent = new Intent(SigninActivity.this, ClassesActivity.class);
                    startActivity(intent);
                }
                else {
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
