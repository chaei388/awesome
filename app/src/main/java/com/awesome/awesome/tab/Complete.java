package com.awesome.awesome.tab;

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
import com.awesome.awesome.adapter.MyRecyclerAdapter;
import com.awesome.awesome.entity.Assignment;
import com.awesome.awesome.sql.SQLiteHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Complete extends Fragment {

    SQLiteHelper sqLiteHelper;
    ArrayList<Assignment> completeList;
    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();

        completeList.clear();
        completeList.addAll(sqLiteHelper.getCompleteAssignments());
        Collections.sort(completeList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a1.getEndDateTime().compareTo(a2.getEndDateTime());
            }
        }.reversed());
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
        completeList = sqLiteHelper.getCompleteAssignments();

        Collections.sort(completeList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return a1.getEndDateTime().compareTo(a2.getEndDateTime());
            }
        }.reversed());

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyRecyclerAdapter(completeList);
        recyclerView.setAdapter(adapter);

        return v;
    }
}
