package com.classroom.zed.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private String input = "";

    private ImageView profile_image_iv;
    private TextView profile_name_tv;
    private RecyclerView profile_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Profile");
        setContentView(R.layout.activity_profile);

        profile_image_iv = findViewById(R.id.profile_image_iv);
        profile_name_tv = findViewById(R.id.profile_name_tv);
        profile_recyclerview = findViewById(R.id.profile_recyclerview);

        Communicator communicator = new Communicator();
        communicator.execute(getIntent().getExtras().getString("ClassCode") + "@@" + getIntent().getExtras().getString("Username") + "@PAP");
        try {
            input = communicator.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        profile_name_tv.setText(input.split("#")[0]);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ProfileActivity_RecyclerAdapter profileActivity_recyclerAdapter = new ProfileActivity_RecyclerAdapter(this, getList(input));
        profile_recyclerview.setLayoutManager(linearLayoutManager);
        profile_recyclerview.setAdapter(profileActivity_recyclerAdapter);
        profile_recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> getList(String input) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < input.split("#").length; i++) {
            if (i != 0)
                list.add(input.split("#")[i]);
        }
        return list;
    }
}
