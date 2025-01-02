package com.example.vehicleclaimapp_userstory3.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vehicleclaimapp_userstory3.dao.ClaimImageDao;
import com.example.vehicleclaimapp_userstory3.model.Claim;
import com.example.vehicleclaimapp_userstory3.dao.ClaimDao;
import com.example.vehicleclaimapp_userstory3.model.ClaimImage;
import com.example.vehicleclaimapp_userstory3.ui.MainActivity;

@Database(entities = {Claim.class, ClaimImage.class}, version = 2)
public abstract class ClaimDatabase extends RoomDatabase {
    public abstract ClaimDao claimDao();
    public abstract ClaimImageDao claimImageDao();


    private static ClaimDatabase instance;

    public static synchronized ClaimDatabase getInstance(MainActivity context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ClaimDatabase.class, "claim_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

