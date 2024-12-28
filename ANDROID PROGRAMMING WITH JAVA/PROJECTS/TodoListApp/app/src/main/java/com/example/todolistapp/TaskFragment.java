package com.example.todolistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {

    private RecyclerView recyclerView;

    private TaskAdapter adapter;
    private  List<Task> taskList=new ArrayList<>();


    private DataBaseHelper dbHelper;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

       recyclerView = view.findViewById(R.id.recycler_view);
       FloatingActionButton fabAdd = view.findViewById(R.id.fab_add);

        // Fetch shared task list

        dbHelper = new DataBaseHelper(getContext());
        List<Task> tasks = dbHelper.getAllTasks();

        adapter = new TaskAdapter(tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
           ((MainActivity) getActivity()).loadFragment(new AddTaskFragment(),"AddTaskFragment");
        });

        return view;
    }
    public void refreshTaskList() {
      //  if (adapter != null) {
            adapter.notifyDataSetChanged();
        //}
    }
    public void addTask(Task task) {
        Toast.makeText(getContext(), "Task Added!", Toast.LENGTH_SHORT).show();
        taskList.add(task); //this can be replaced with database
     //   ((MainActivity) getActivity()).setTaskList(((MainActivity) getActivity()).getTaskList());
        adapter.notifyDataSetChanged();
    }


    /*public void addTask(String taskTitle) {
        taskList.add(new Task(taskTitle));
        adapter.notifyDataSetChanged();
        System.out.println(taskList);
        Toast.makeText(getContext(), "Task Added!"+taskList.toString(), Toast.LENGTH_SHORT).show();
    }*/
}
