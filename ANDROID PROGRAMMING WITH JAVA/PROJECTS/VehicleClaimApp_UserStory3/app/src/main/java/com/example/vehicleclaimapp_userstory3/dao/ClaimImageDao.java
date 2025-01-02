package com.example.vehicleclaimapp_userstory3.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vehicleclaimapp_userstory3.model.ClaimImage;

import java.util.List;

@Dao
public interface ClaimImageDao {
    @Insert
    void insertImage(ClaimImage claimImage);

    @Query("SELECT * FROM claim_images WHERE claim_id = :claimId")
    List<ClaimImage> getImagesByClaimId(String claimId);
}

