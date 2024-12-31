package com.example.vehicleinsuranceapp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.vehicleinsuranceapp.model.Claim;
import com.example.vehicleinsuranceapp.dao.ClaimDao;

@Database(entities = {Claim.class}, version = 1)
public abstract class ClaimDatabase extends RoomDatabase {
    public abstract ClaimDao claimDao();

    private static ClaimDatabase instance;

    public static synchronized ClaimDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ClaimDatabase.class, "claim_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
