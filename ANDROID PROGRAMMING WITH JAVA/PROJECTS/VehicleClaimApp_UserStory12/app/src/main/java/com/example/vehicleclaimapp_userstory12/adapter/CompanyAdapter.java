package com.example.vehicleclaimapp_userstory12.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vehicleclaimapp_userstory12.R;
import com.example.vehicleclaimapp_userstory12.model.CompanyUpdate;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {
    private List<CompanyUpdate> companyList;

    public CompanyAdapter(List<CompanyUpdate> companyList) {
        this.companyList = companyList;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        CompanyUpdate company = companyList.get(position);
        holder.nameTextView.setText(company.getName());
        holder.descriptionTextView.setText(company.getDescription());
        holder.contactTextView.setText(company.getContact());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView, contactTextView;

        public CompanyViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            contactTextView = itemView.findViewById(R.id.contactTextView);
        }
    }
}

