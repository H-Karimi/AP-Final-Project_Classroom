package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassWorkActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Class Work");
        setContentView(R.layout.activity_class_work);

        recyclerView = findViewById(R.id.classwork_outer_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ClassWorkActivity_RecyclerAdapter_Outer classWorkActivity_recyclerAdapter_outer = new ClassWorkActivity_RecyclerAdapter_Outer(this, getList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(classWorkActivity_recyclerAdapter_outer);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("asdfghjk");
        list.add("12345678");
        list.add("qwertyui");
        list.add("zxcvbnm,");
        return list;
    }
}

