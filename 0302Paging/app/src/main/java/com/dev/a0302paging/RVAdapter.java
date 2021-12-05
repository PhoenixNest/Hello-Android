package com.dev.a0302paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.a0302paging.db.Student;

public class RVAdapter extends PagedListAdapter<Student, RVAdapter.RV_VH> {

    protected RVAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentNumber() == newItem.getStudentNumber();
            }
        });
    }

    @NonNull
    @Override
    public RV_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new RV_VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RV_VH holder, int position) {
        Student student = getItem(position);
        if (student != null) {
            holder.textView.setText(String.valueOf(student.getStudentNumber()));
        } else {
            holder.textView.setText("Loading");
        }
    }

    static class RV_VH extends RecyclerView.ViewHolder {
        TextView textView;

        public RV_VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
