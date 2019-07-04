package com.classroom.zed.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity implements ClassesActivity_RecyclerAdapter.OnItemListener {

    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Classes");
        setContentView(R.layout.activity_classes);
        myRecyclerView = findViewById(R.id.classes_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ClassesActivity_RecyclerAdapter classesActivity_recyclerAdapter = new ClassesActivity_RecyclerAdapter(this, getList(), this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(classesActivity_recyclerAdapter);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> getList(){
        List<String> strings = new ArrayList<>();
        strings.add("AP#Computer#Mamad Mamadi");
        strings.add("Mathematics#3124789471#25 Students");
        strings.add("Geography#Lorem Ipsum#Asghar");
        return strings;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_classes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Create class")){
            Intent intent = new Intent(ClassesActivity.this, CreateClassActivity.class);
            startActivity(intent);
        }
        else if(item.getTitle().equals("Join class")){
            Intent intent = new Intent(ClassesActivity.this, JoinClassActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Clicked." + position, Toast.LENGTH_LONG).show();
    }
}
