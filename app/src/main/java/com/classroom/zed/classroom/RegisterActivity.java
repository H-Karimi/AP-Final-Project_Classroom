package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    EditText username_et;
    EditText password_et;
    TextView usernameError_tv;
    TextView passwordError_tv;
    Button register_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        usernameError_tv = findViewById(R.id.usernameError_tv);
        passwordError_tv = findViewById(R.id.passwordError_tv);
        register_b = findViewById(R.id.register_b);

        username_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){

                    if (username_et.getText().toString().trim().equals(""))
                        usernameError_tv.setText("Username field cannot be empty.");
                    else {
                        String input = "";
                        Communicator communicator = new Communicator();
                        communicator.execute("#C" + username_et.getText().toString().trim());
                        try {
                            input = communicator.get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(input.equals("0"))
                            usernameError_tv.setText("");
                        else if(input.equals("1"))
                            usernameError_tv.setText("This username in occupied. Try another.");
                    }
                }
            }
        });

        password_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (password_et.getText().toString().trim().equals(""))
                        passwordError_tv.setText("Password field cannot be empty.");
                    else if(password_et.getText().toString().trim().length() < 6)
                        passwordError_tv.setText("Password should be at least 6 characters.");
                    else
                        passwordError_tv.setText("");
                }
            }
        });
    }
}
