package com.example.todolistapp;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddTaskFragment extends Fragment {

    public interface AddTaskListener {
        void onTaskAdded(String taskTitle);
    }

    String taskTitle;


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

                if (getActivity() instanceof MainActivity) {
                    TaskFragment fragment = (TaskFragment) getActivity()
                            .getSupportFragmentManager()
                            .findFragmentByTag("TaskFragment");
                    Task task = new Task(taskTitle);
                        ((TaskFragment) fragment).addTask(task);
                        getActivity().getSupportFragmentManager().popBackStack();
                        System.out.println(fragment);
                   // }

                }

            }
        });

        return view;
    }
    public void addTask(String taskTitle) {

    //    taskList.add(new Task(taskTitle));
      //  adapter.notifyDataSetChanged();

    }
}
