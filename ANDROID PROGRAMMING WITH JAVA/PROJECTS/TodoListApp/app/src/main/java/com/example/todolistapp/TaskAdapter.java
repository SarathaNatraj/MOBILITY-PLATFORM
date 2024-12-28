package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }
    private DataBaseHelper dbHelper;

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        dbHelper = new DataBaseHelper(parent.getContext());
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.textViewTitle.setText(task.getTitle());
        holder.textViewTitleDesc.setText(task.getDescription());
        holder.delButton.setOnClickListener(v->{
            dbHelper.deleteTask(task.getId());

            // Remove task from the list and notify adapter
            taskList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskList.size());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,textViewTitleDesc;
        ImageView delButton;
        

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewTitleDesc = itemView.findViewById(R.id.text_view_title_desc);
            delButton = itemView.findViewById(R.id.delete);

        }
    }
}
