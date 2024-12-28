package com.example.vehicleclaimapp.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vehicleclaimapp.dao.claim.ClaimDao;
import com.example.vehicleclaimapp.model.Claim;

@Database(entities = {Claim.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClaimDao claimDao();
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"claim_database").build(); //create database claim_database, create table claims(props)
        }
        return instance;

    }



}
