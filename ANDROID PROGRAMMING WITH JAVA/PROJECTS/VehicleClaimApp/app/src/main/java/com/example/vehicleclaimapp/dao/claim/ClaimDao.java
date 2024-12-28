package com.example.vehicleclaimapp.dao.claim;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.vehicleclaimapp.model.Claim;

@Dao
public interface ClaimDao {

    @Insert
    void insertClaim(Claim claim);

    @Query("SELECT * FROM Claim")
    List<Claim> getAllClaims();


}
