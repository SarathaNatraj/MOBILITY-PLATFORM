package com.example.vehicleclaimapp_userstory3.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vehicleclaimapp_userstory3.R;
import com.example.vehicleclaimapp_userstory3.model.ClaimImage;

public class ClaimImageAdapter extends RecyclerView.Adapter<ClaimImageAdapter.ImageViewHolder> {

    private List<ClaimImage> imageList;
    private Context context;

    // Constructor
    public ClaimImageAdapter(Context context, List<ClaimImage> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    // ViewHolder to represent each item in the list
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.claimimageView);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_claimimage, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ClaimImage capturedImage = imageList.get(position);

        // Use Glide to load the image into ImageView - cache image loading
        Glide.with(context)
                .load(capturedImage.getImagePath()) // Load image from the path
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    // Add an image to the list
    public void addImage(ClaimImage image) {
        imageList.add(image);
        notifyItemInserted(imageList.size() - 1);
    }
}

