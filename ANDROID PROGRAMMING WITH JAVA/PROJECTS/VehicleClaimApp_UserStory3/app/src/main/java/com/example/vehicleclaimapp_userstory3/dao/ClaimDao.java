package com.example.vehicleclaimapp_userstory3.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vehicleclaimapp_userstory3.model.Claim;

import java.util.List;

@Dao
public interface ClaimDao {
    @Insert
    void insertClaim(Claim claim);

    @Query("SELECT * FROM claims")
    List<Claim> getAllClaims();
}
