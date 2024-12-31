package com.example.vehicleinsuranceapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vehicleinsuranceapp.model.Claim;

import java.util.List;

@Dao
public interface ClaimDao {
    @Insert
    void insert(Claim claim);

    @Query("SELECT * FROM claims")
    List<Claim> getAllClaims();
}
