package com.classroom.zed.classroom;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ClassPageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Class");
        setContentView(R.layout.activity_class_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.navigation_notification:
                            setContentView(R.layout.activity_class_page);
                            return true;

                        case R.id.navigation_classwork:
                            setContentView(R.layout.activity_classwork);
                            return true;

                        case R.id.navigation_people:
                            setContentView(R.layout.activity_class_page);
                            return true;

                        default:return true;
                    }
                });
    }
}
