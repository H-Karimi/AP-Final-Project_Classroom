package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ClassPageActivity extends AppCompatActivity {

    private String input = "";

    private BottomNavigationView bottomNavigationView;
    private ClassInfo classInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_page);
        classInfo = new ClassInfo(getIntent().getExtras().getString("ClassCode"), getIntent().getExtras().getString("ClassState"));

        Communicator communicator = new Communicator();
        communicator.execute(classInfo.getCode() + "@@@CP");
        try {
            input = communicator.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle(input.split("#")[1].split("~")[0]);

        loadFragment(new ClassworkFragment());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_classwork);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.navigation_notification:
                            loadFragment(new NotificationsFragment());
                            return true;

                        case R.id.navigation_classwork:
                            loadFragment(new ClassworkFragment());
                            return true;

                        case R.id.navigation_people:
                            loadFragment(new PeopleFragment());
                            return true;

                        default:
                            return true;
                    }
                });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_class_page, menu);
        if (classInfo.getState().equals("T")) {
            menu.findItem(R.id.class_page_info).setVisible(false);
            menu.findItem(R.id.class_page_settings).setVisible(true);
        }
        if (classInfo.getState().equals("S")) {
            menu.findItem(R.id.class_page_settings).setVisible(false);
            menu.findItem(R.id.class_page_info).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        if (item.getTitle().equals("Classes")) {
            intent = new Intent(ClassPageActivity.this, ClassesActivity.class);
        } else if (item.getTitle().equals("About us")) {
            intent = new Intent(ClassPageActivity.this, AboutUsActivity.class);
        } else if (item.getTitle().equals("Info")) {
            intent = new Intent(ClassPageActivity.this, ClassInfoActivity.class);
            intent.putExtra("ClassCode", classInfo.getCode());
        } else if (item.getTitle().equals("Settings")) {
            intent = new Intent(ClassPageActivity.this, ClassSettingsActivity.class);
            intent.putExtra("ClassCode", classInfo.getCode());
        }
        if (intent != null)
            startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public String getClassInfo() {
        return input;
    }
}
