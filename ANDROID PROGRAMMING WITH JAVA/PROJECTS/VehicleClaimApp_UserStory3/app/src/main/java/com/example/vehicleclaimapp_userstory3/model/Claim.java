package com.example.vehicleclaimapp_userstory3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "claims")
public class Claim {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", title='" + title + '\'' +

                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
