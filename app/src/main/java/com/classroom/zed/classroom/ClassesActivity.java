package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ClassesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_classes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Create class")){
            Intent intent = new Intent(ClassesActivity.this, CreatClassActivity.class);
            startActivity(intent);
        }
        else if(item.getTitle().equals("Join class")){
            Intent intent = new Intent(ClassesActivity.this, JoinClassActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
