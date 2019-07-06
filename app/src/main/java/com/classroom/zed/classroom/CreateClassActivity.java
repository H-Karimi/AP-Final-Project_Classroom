package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateClassActivity extends AppCompatActivity {
    private String input = "";

    private EditText name_et;
    private TextView nameError_tv;
    private EditText section_et;
    private TextView sectionError_tv;
    private EditText room_et;
    private TextView roomError_tv;

    private MenuItem create_sub_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Create class");
        setContentView(R.layout.activity_creat_class);

        name_et = findViewById(R.id.name_et);
        nameError_tv = findViewById(R.id.nameError_tv);
        section_et = findViewById(R.id.section_et);
        sectionError_tv = findViewById(R.id.sectionError_tv);
        room_et = findViewById(R.id.room_et);
        roomError_tv = findViewById(R.id.roomError_tv);

        create_sub_menu = findViewById(R.id.create_submenu);

        sectionError_tv.setTextColor(sectionError_tv.getHintTextColors());

        name_et.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (name_et.getText().toString().trim().equals(""))
                    nameError_tv.setText("Class name field cannot be empty.");
                else
                    nameError_tv.setText("");
            }
        });

        section_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    sectionError_tv.setText("");
                else
                    sectionError_tv.setText("70 characters at most.");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        room_et.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (room_et.getText().toString().trim().equals(""))
                    roomError_tv.setText("Room number field cannot be empty.");
                else
                    roomError_tv.setText("");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_class, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.refresh_create_class_menu:
                break;
            case R.id.aboutus_create_class_menu:
                intent = new Intent(CreateClassActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.create_submenu:
                if (name_et.getText().toString().trim().length() > 0 && room_et.getText().toString().trim().length() > 0) {
                    Communicator communicator = new Communicator();
                    communicator.execute("M#" + name_et.getText().toString().trim() + "#" + section_et.getText().toString().trim() + "#" + room_et.getText().toString().trim());
                    try {
                        input = communicator.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(CreateClassActivity.this, "Class created", Toast.LENGTH_LONG).show();
                    intent = new Intent(CreateClassActivity.this, ClassPageActivity.class);
                    intent.putExtra("ClassCode", input.split("#")[1]);
                    intent.putExtra("ClassState", "T");
                    startActivity(intent);
                } else {
                    if (name_et.getText().toString().trim().equals(""))
                        nameError_tv.setText("Class name field cannot be empty.");
                    else
                        nameError_tv.setText("");
                    if (room_et.getText().toString().trim().equals(""))
                        roomError_tv.setText("Room number field cannot be empty.");
                    else
                        roomError_tv.setText("");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
