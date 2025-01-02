package com.example.vehicleclaimapp_userstory3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vehicleclaimapp_userstory3.R;
import com.example.vehicleclaimapp_userstory3.model.Claim;

import java.util.List;

public class ClaimAdapter extends RecyclerView.Adapter<ClaimAdapter.ClaimViewHolder> {
    private List<Claim> claimList;
    private Context context;

    public ClaimAdapter(Context context, List<Claim> claimList) {
        this.context = context;
        this.claimList = claimList;
    }

    @NonNull
    @Override
    public ClaimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_claim, parent, false);
        return new ClaimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimViewHolder holder, int position) {
        Claim claim = claimList.get(position);
        holder.title.setText(claim.title);

        // Load image using Glide
      /*  Glide.with(context)
                .load(claim.imagePath)
                .placeholder(R.drawable.placeholder) // Replace with your placeholder image
                .into(holder.imageView);
*/    }

    @Override
    public int getItemCount() {
        return claimList.size();
    }

    public static class ClaimViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public ClaimViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
