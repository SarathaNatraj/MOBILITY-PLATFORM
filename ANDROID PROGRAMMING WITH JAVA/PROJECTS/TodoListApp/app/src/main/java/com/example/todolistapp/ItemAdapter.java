package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialise your item_task.xml
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }


    static class ItemViewHolder extends  RecyclerView.ViewHolder{

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
