package com.awesome.awesome.tab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.awesome.awesome.R;
import com.awesome.awesome.activity.AssignmentForm;
import com.awesome.awesome.adapter.MyRecyclerAdapter;
import com.awesome.awesome.entity.Assignment;
import com.awesome.awesome.sql.SQLiteHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Incomplete extends Fragment {
    SQLiteHelper sqLiteHelper;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;
    ArrayList<Assignment> todoList;

    @Override
    public void onResume() {
        super.onResume();

        todoList.clear();
        todoList.addAll(sqLiteHelper.getIncompleteAssignments());
        Collections.sort(todoList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a1.getEndDateTime().compareTo(a2.getEndDateTime());
            }
        });
        adapter.notifyDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.incomplete_fragment, container, false);

        if (v == null) {
            Log.e("IncompleteFragment", "Layout inflation 실패!");
        } else {
            Log.d("IncompleteFragment", "Layout inflation 성공!");
        }

        sqLiteHelper = new SQLiteHelper(getContext());
        todoList = sqLiteHelper.getIncompleteAssignments();
        Collections.sort(todoList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a1.getEndDateTime().compareTo(a2.getEndDateTime());
            }
        });

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyRecyclerAdapter(todoList);
        recyclerView.setAdapter(adapter);

        // FloatingActionButton 설정
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AssignmentForm.class);

                startActivity(intent);
            }
        });

        return v;
    }
}
