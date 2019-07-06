package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity {

    private String input = "";

    private RecyclerView myRecyclerView;
    private TextView classes_noclass_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Classes");
        setContentView(R.layout.activity_classes);

        classes_noclass_tv = findViewById(R.id.classes_noclass_tv);
        myRecyclerView = findViewById(R.id.classes_recyclerView);
        refresh();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    public void refresh() {
        Communicator communicator = new Communicator();
        communicator.execute("@@@MP");
        try {
            input = communicator.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (input.equals("##.")) {
            classes_noclass_tv.setVisibility(View.VISIBLE);
        } else {
            classes_noclass_tv.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            ClassesActivity_RecyclerAdapter classesActivity_recyclerAdapter = new ClassesActivity_RecyclerAdapter(this, getList(input));
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myRecyclerView.setAdapter(classesActivity_recyclerAdapter);
            myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    private List<String> getList(String string) {
        List<String> list = new ArrayList<>();
        String[] teacherClasses = string.split("#")[0].split("~");
        String[] studentClasses = string.split("#")[1].split("~");

        for (int i = 0; i < teacherClasses.length; i++) {
            String s = teacherClasses[i];
            if (s.length() > 0)
                list.add(s.split("!")[0] + "#" + s.split("!")[1] + "#" + s.split("!")[2] + " students#" + s.split("!")[3] + "#T");
        }
        for (int i = 0; i < studentClasses.length; i++) {
            String s = studentClasses[i];
            if (s.length() > 0)
                list.add(s.split("!")[0] + "#" + s.split("!")[1] + "#" + s.split("!")[2] + "#" + s.split("!")[3] + "#S");
        }

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_classes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Refresh")) {
            refresh();
        } else if (item.getTitle().equals("About us")) {
            Intent intent = new Intent(ClassesActivity.this, AboutUsActivity.class);
            startActivity(intent);
        } else if (item.getTitle().equals("Create class")) {
            Intent intent = new Intent(ClassesActivity.this, CreateClassActivity.class);
            startActivity(intent);
        } else if (item.getTitle().equals("Join class")) {
            Intent intent = new Intent(ClassesActivity.this, JoinClassActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}