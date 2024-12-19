package com.awesome.awesome.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awesome.awesome.R;
import com.awesome.awesome.activity.AssignmentForm;
import com.awesome.awesome.entity.Assignment;
import com.awesome.awesome.sql.SQLiteHelper;

import android.widget.Button;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Assignment> items;

    public MyRecyclerAdapter(List<Assignment> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.assignmentID.setText(Integer.toString(items.get(position).getID()));
        holder.assignmentName.setText(items.get(position).getName());
        // 임시로 string으로 출력
        String endDateTime = items.get(position).getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        holder.assignmentEndDateTime.setText(endDateTime);
        holder.assignmentStatus.setText(items.get(position).getStatus().toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView assignmentID;
        TextView assignmentName;
        TextView assignmentEndDateTime;
        TextView assignmentStatus;
        Button modifyBtn;
        SQLiteHelper sqLiteHelper;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentID = itemView.findViewById(R.id.assignmentID);
            assignmentName = itemView.findViewById(R.id.listAssignmentName);
            assignmentEndDateTime = itemView.findViewById(R.id.listAssignmentDate);
            assignmentStatus = itemView.findViewById(R.id.listAssignmentStatus);
            modifyBtn = itemView.findViewById(R.id.modifyBtn);

            modifyBtn = itemView.findViewById(R.id.modifyBtn);
            modifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AssignmentForm.class);

                    int id = Integer.parseInt(assignmentID.getText().toString());

                    sqLiteHelper = new SQLiteHelper(view.getContext());
                    Assignment assignment = sqLiteHelper.getAssignment(id);

                    if (assignment == null) {
                        System.out.println("Select Error!!!!!!!!!!!");
                    }

                    intent.putExtra("Assignment", assignment);

                    ((Activity)view.getContext()).startActivity(intent);
                }
            });
        }
    }
}
