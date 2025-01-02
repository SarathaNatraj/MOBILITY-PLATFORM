package com.example.vehicleclaimapp_userstory3.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "claim_images")
public class ClaimImage {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "claim_id")
    private String claimId;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }
// Getters and Setters
}
