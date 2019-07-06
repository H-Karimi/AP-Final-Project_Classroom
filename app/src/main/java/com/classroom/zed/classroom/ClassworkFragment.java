package com.classroom.zed.classroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClassworkFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private String input = "";

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton = null;
    private TextView classwork_noclasswork_tv;
    private View rootView;
    private ClassInfo classInfo;

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_classwork, null);
        classwork_noclasswork_tv = rootView.findViewById(R.id.classwork_noclasswork_tv);
        floatingActionButton = rootView.findViewById(R.id.floatingActionButton);

        ClassPageActivity activity = (ClassPageActivity) getActivity();
        input = activity.getClassInfo();

        classInfo = new ClassInfo(input.split("#")[1].split("~")[1], input.split("#")[0]);

        if (input.split("#")[2].isEmpty()) {
            classwork_noclasswork_tv.setVisibility(View.VISIBLE);
        } else {
            classwork_noclasswork_tv.setVisibility(View.INVISIBLE);
            recyclerView = rootView.findViewById(R.id.classwork_outer_recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
            ClassWorkActivity_RecyclerAdapter_Outer adapter = new ClassWorkActivity_RecyclerAdapter_Outer(rootView.getContext(), getList(input.split("#")[2]), classInfo);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        if (classInfo.getState().equals("T")) {
            floatingActionButton.setVisibility(View.VISIBLE);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(rootView.getContext(), v);
                    popupMenu.setOnMenuItemClickListener(ClassworkFragment.this);
                    popupMenu.inflate(R.menu.menu_fab);
                    popupMenu.show();
                }
            });
        } else if (classInfo.getState().equals("S")) {
            floatingActionButton.setVisibility(View.GONE);
        }


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getTitle().equals("New classwork")) {
            Intent intent = new Intent(getContext(), CreateClassworkActivity.class);
            intent.putExtra("ClassCode", classInfo.getCode());
            intent.putExtra("ClassState", classInfo.getState());
            startActivity(intent);
            return true;
        } else if (menuItem.getTitle().equals("New topic")) {

            return true;
        }
        return false;
    }

    private List<String> getList(String s) {
        List<String> list = new ArrayList<>();
        String[] topics = s.split("~");
        for (int i = 0; i < topics.length; i++) {
            if (!topics[i].isEmpty())
                list.add(topics[i]);
        }
        return list;
    }


    void refresh() {

    }
}
