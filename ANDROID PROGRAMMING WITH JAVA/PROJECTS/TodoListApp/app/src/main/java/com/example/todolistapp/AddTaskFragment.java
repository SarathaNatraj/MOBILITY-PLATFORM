package com.example.todolistapp;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddTaskFragment extends Fragment {

    public interface AddTaskListener {
        void onTaskAdded(String taskTitle);
    }
    private AddTaskListener listener;

    String taskTitle;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddTaskListener) {
            listener = (AddTaskListener) context;
            TaskFragment taskFragment = (TaskFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
            if (taskFragment != null) {
                taskFragment.addTask(taskTitle);
            }
        } else {
          //  throw new ClassCastException(context.toString() + " must implement AddTaskListener");

        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        EditText editTextTask = view.findViewById(R.id.edit_text_task);
        Button btnAddTask = view.findViewById(R.id.btn_add_task);

        btnAddTask.setOnClickListener(v -> {
         taskTitle   = editTextTask.getText().toString().trim();
            if (TextUtils.isEmpty(taskTitle)) {
                editTextTask.setError("Task cannot be empty!");
            } else {
               // listener.onTaskAdded(taskTitle);
                TaskFragment taskFragment = (TaskFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
                if (taskFragment != null) {
                    taskFragment.addTask(taskTitle);
                }
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
