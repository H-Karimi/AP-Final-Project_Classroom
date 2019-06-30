package com.classroom.zed.classroom;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    ImageView image_iv;
    EditText username_et;
    EditText password_et;
    TextView usernameError_tv;
    TextView passwordError_tv;
    Button register_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign up");
        setContentView(R.layout.activity_register);
        image_iv = findViewById(R.id.image_iv);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        usernameError_tv = findViewById(R.id.usernameError_tv);
        passwordError_tv = findViewById(R.id.passwordError_tv);
        register_b = findViewById(R.id.register_b);

        image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
            }
        });

        username_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){

                    if (username_et.getText().toString().trim().equals(""))
                        usernameError_tv.setText("Username field cannot be empty.");
                    else {
                        usernameError_tv.setText("");
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

        register_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username_et.getText().toString().trim().equals("")  &&  !password_et.getText().toString().trim().equals("")  &&
                        password_et.getText().toString().trim().length() > 5) {
                    Intent intent = new Intent(RegisterActivity.this, ClassesActivity.class);
                    startActivity(intent);
                }
                else{
                    if (password_et.getText().toString().trim().equals(""))
                        passwordError_tv.setText("Password field cannot be empty.");
                    else if(password_et.getText().toString().trim().length() < 6)
                        passwordError_tv.setText("Password should be at least 6 characters.");
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

    //Image Settings
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == 1) {
                    Uri selectedImageUri = data.getData();
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                    }
                    // Set the image in ImageView
                    image_iv.setImageURI(selectedImageUri);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
