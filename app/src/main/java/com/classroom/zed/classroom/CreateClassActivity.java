package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateClassActivity extends AppCompatActivity {
    EditText name_et;
    TextView nameError_tv;
    EditText section_et;
    TextView sectionError_tv;
    EditText room_et;
    TextView roomError_tv;
    EditText subject_et;

    MenuItem create_sub_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Create class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_creat_class);

        name_et = findViewById(R.id.name_et);
        nameError_tv = findViewById(R.id.nameError_tv);
        section_et = findViewById(R.id.section_et);
        sectionError_tv = findViewById(R.id.sectionError_tv);
        room_et = findViewById(R.id.room_et);
        roomError_tv = findViewById(R.id.roomError_tv);
        subject_et = findViewById(R.id.subject_et);

        create_sub_menu = findViewById(R.id.create_submenu);

        sectionError_tv.setTextColor(sectionError_tv.getHintTextColors());

        name_et.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                if(name_et.getText().toString().trim().equals(""))
                    nameError_tv.setText("Class name field cannot be empty.");
                else
                    nameError_tv.setText("");
            }
        });
        name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(name_et.getText().toString().trim().length() > 0  &&  room_et.getText().toString().trim().length() > 0)
                    create_sub_menu.setEnabled(true);
            }
        });

        section_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0)
                    sectionError_tv.setText("");
                else
                    sectionError_tv.setText("70 characters at most.");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        room_et.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                if(room_et.getText().toString().trim().equals(""))
                    roomError_tv.setText("Room number field cannot be empty.");
                else
                    roomError_tv.setText("");
            }
        });
        room_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(name_et.getText().toString().trim().length() > 0  &&  room_et.getText().toString().trim().length() > 0)
                    create_sub_menu.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_class, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
