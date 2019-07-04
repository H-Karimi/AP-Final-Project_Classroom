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

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Class");
        setContentView(R.layout.activity_class_page);

        loadFragment(new ClassworkFragment());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
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

                        default:return true;
                    }
                });
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_class_page, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Classes")){
            Intent intent = new Intent(ClassPageActivity.this, ClassesActivity.class);
            startActivity(intent);
        }
        else if(item.getTitle().equals("Info")){
            Intent intent = new Intent(ClassPageActivity.this, ClassInfoActivity.class);
            startActivity(intent);
        }
        else if(item.getTitle().equals("Settings")){
            Intent intent = new Intent(ClassPageActivity.this, ClassSettingsActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

}
