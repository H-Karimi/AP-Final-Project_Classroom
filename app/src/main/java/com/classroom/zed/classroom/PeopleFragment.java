package com.classroom.zed.classroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment {

    private String input = "";

    private ClassInfo classInfo;

    private RecyclerView recyclerView;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_people, null);

        ClassPageActivity activity = (ClassPageActivity) getActivity();
        input = activity.getClassInfo();
        Log.d("Class::", input);

        classInfo = new ClassInfo(input.split("#")[1].split("~")[1], input.split("#")[0]);

        recyclerView = rootView.findViewById(R.id.people_outer_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        PeopleFragment_RecyclerAdapter_Outer adapter = new PeopleFragment_RecyclerAdapter_Outer(rootView.getContext(), getList(input), classInfo);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

//    private List<String> getList(){
//        List<String> list = new ArrayList<>();
//        list.add("Teachers");
//        list.add("Students");
//        return list;
//    }

    private List<String> getList(String input) {
        List<String> list = new ArrayList<>();

        String teachers = "Teachers";
        for (int i = 0; i < input.split("#")[3].split(";").length; i++) {
            String teacher = input.split("#")[3].split(";")[i];
            if (!teacher.isEmpty())
                teachers += ("#" + teacher);
        }

        String students = "Students";
        for (int i = 0; i < input.split("#")[4].split(";").length; i++) {
            String student = input.split("#")[4].split(";")[i];
            if (!student.isEmpty())
                students += ("#" + student);
        }

        list.add(teachers);
        if (students.length() > 8)
            list.add(students);
        return list;
    }
}
